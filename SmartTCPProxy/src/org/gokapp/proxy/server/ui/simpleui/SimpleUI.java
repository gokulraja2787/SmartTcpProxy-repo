package org.gokapp.proxy.server.ui.simpleui;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.TableCellRenderer;

import org.gokapp.proxy.server.Main;
import org.gokapp.proxy.server.constants.ProxyConstants;
import org.gokapp.proxy.server.exception.ServiceException;
import org.gokapp.proxy.server.io.GenericReadWriteService;
import org.gokapp.proxy.server.io.ReadWriteServiceFactory;
import org.gokapp.proxy.server.manager.ExitManager;
import org.gokapp.proxy.server.ui.ProxyAppUI;
import org.gokapp.proxy.server.ui.simpleui.events.AdaptiveWindowEvent;
import org.gokapp.proxy.server.ui.simpleui.events.CustomButtonEvent;
import org.gokapp.proxy.server.ui.simpleui.listener.ProxyTableMouseListener;
import org.gokapp.proxy.server.ui.simpleui.renderer.ProxyTableButtonRenderer;
import org.gokapp.proxy.server.util.ProxyUtil;

/**
 * Simple UI look and feel.
 * 
 * @author grangarajan
 *
 */
class SimpleUI implements ProxyAppUI {

	/**
	 * 
	 * @author grangarajan
	 *
	 */
	private class IOImplVO {

		Integer code;
		String implName;

		/**
		 * @param code
		 * @param implName
		 */
		public IOImplVO(Integer code, String implName) {
			super();
			this.code = code;
			this.implName = implName;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return implName;
		}

	};

	private IOImplVO[] implList;

	/**
	 * Table that holds Port forward details
	 */
	private JTable portForwardTabel;

	/**
	 * Holds main instance
	 */
	private Main mainInstance;

	/**
	 * 
	 */
	SimpleUI() {
		mainInstance = Main.getInstance();
		Map<Integer, Class<? extends GenericReadWriteService>> implMap = ReadWriteServiceFactory
				.getFactoryInstance().getAvailableIOService();
		implList = new IOImplVO[implMap.size()];
		Set<Integer> keys = implMap.keySet();
		int i = 0;
		for (Integer key : keys) {
			implList[i++] = new IOImplVO(key, implMap.get(key).getSimpleName());
		}
		keys = null;
		implMap = null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gokapp.proxy.server.ui.ProxyAppUI#start()
	 */
	@Override
	public void startEvent() {
		ProxyUIFrame mainUI = new ProxyUIFrame();
		setUpEventsForMainFrame(mainUI); // Setup events for main UI window
		setUpUIComponentsForMainFrame(mainUI); // Setup UI components for main
												// UI window
		invokeLater(mainUI); // Initiate main UI

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gokapp.proxy.server.ui.ProxyAppUI#shutdown(int)
	 */
	@Override
	public void shutdownEvent(int statusCode) {
		AdaptiveWindowEvent.shutdownService();

	}

	/**
	 * Set up port forward table
	 * 
	 * @return
	 */
	private JScrollPane setUpPortWordTabel() {

		if (portForwardTabel == null) {
			portForwardTabel = new JTable(new ProxyUITableModel(
					portForwardTabel));
			TableCellRenderer buttonRenderer = new ProxyTableButtonRenderer();
			portForwardTabel.getColumn("Remove")
					.setCellRenderer(buttonRenderer);
			portForwardTabel.addMouseListener(new ProxyTableMouseListener(
					portForwardTabel));
		}

		JScrollPane portForwardTablePanel = new JScrollPane(portForwardTabel);
		portForwardTablePanel.setLayout(new ScrollPaneLayout());
		// portForwardTabel.setSurrendersFocusOnKeystroke(true);
		portForwardTabel
				.setPreferredScrollableViewportSize(new java.awt.Dimension(500,
						300));

		return portForwardTablePanel;
	}

	/**
	 * Setup UI for Main Frame
	 * 
	 * @param mainUI
	 */
	private void setUpUIComponentsForMainFrame(ProxyUIFrame mainUI) {
		GridLayout mainLayout = new GridLayout(3, 1);
		mainUI.setSize(550, 600); // 400 X 600
		mainUI.setLayout(mainLayout);
		JPanel mainPanel = new JPanel();

		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new CardLayout());
		headerPanel
				.add(new JLabel(ProxyConstants.CONG_PORT_FWD, JLabel.CENTER));

		JPanel controlPanel = new JPanel();
		setUpControlPane(controlPanel);

		JScrollPane portForwardTablePanel = setUpPortWordTabel();
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tablePanel.add(new JLabel(ProxyConstants.ACTIVE_SERVICE));

		tablePanel.add(portForwardTablePanel);

		mainPanel.add(controlPanel);

		mainUI.add(headerPanel);
		mainUI.add(mainPanel);
		mainUI.add(tablePanel);
		// mainUI.pack();
	}

	/**
	 * Sets up main panel
	 * 
	 * @param controlPanel
	 */
	private void setUpControlPane(final JPanel controlPanel) {

		GridLayout mainLayout = new GridLayout(5, 2);

		controlPanel.setLayout(mainLayout);

		controlPanel.add(new JLabel(ProxyConstants.DESTI_HOST));
		final JTextField destiHost = new JTextField();
		controlPanel.add(destiHost);
		controlPanel.add(new JLabel(ProxyConstants.DESTI_PORT));
		final JTextField destiPort = new JTextField();
		controlPanel.add(destiPort);
		final JTextField listeningPort = new JTextField();
		controlPanel.add(new JLabel(ProxyConstants.SELECT_SERVICE));
		// final String[] implServices = { "Simple TCP", "Mock SMTP" };
		final JComboBox<IOImplVO> implCombo = new JComboBox<IOImplVO>(implList);
		controlPanel.add(implCombo);
		JButton addPortForward = new JButton(ProxyConstants.ADD_FWD_MSG);
		addPortForward.addActionListener(new CustomButtonEvent(new Runnable() {

			@Override
			public void run() {
				try {
					IOImplVO selectedIOMethod = (IOImplVO) implCombo
							.getSelectedItem();
					mainInstance.setupPortForward(selectedIOMethod.code,
							Integer.valueOf(listeningPort.getText()),
							destiHost.getText(),
							Integer.parseInt(destiPort.getText()));
					listeningPort.setText(ProxyConstants.EMPTY);
					destiHost.setText(ProxyConstants.EMPTY);
					destiPort.setText(ProxyConstants.EMPTY);
				} catch (NumberFormatException formatException) {
					System.err.println(formatException.getMessage());
					formatException.printStackTrace();
					ProxyUtil.showErrorDialog(ProxyConstants.WRONG_PORT,
							controlPanel);
				} catch (ServiceException serviceException) {
					System.err.println(serviceException.getMessage());
					ProxyUtil.showErrorDialog(serviceException.getMessage(),
							controlPanel);
				}

			}
		}));
		controlPanel.add(new JLabel(ProxyConstants.LISTEN_MSG));
		controlPanel.add(listeningPort);
		controlPanel.add(addPortForward);
	}

	/**
	 * Setup events for main window
	 * 
	 * @param uiFrame
	 */
	private void setUpEventsForMainFrame(ProxyUIFrame uiFrame) {
		AdaptiveWindowEvent mainUIWindowEvent = new AdaptiveWindowEvent();
		mainUIWindowEvent.setWindowClosingAction(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				ExitManager.globalExit(ProxyConstants.SAFE_EXIT_CODE);
				return ProxyConstants.SAFE_EXIT_CODE;
			}
		});
		uiFrame.addWindowListener(mainUIWindowEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gokapp.proxy.server.ui.ProxyAppUI#fireDataChange()
	 */
	@Override
	public void fireDataChange() {
		if (null != portForwardTabel) {
			((ProxyUITableModel) portForwardTabel.getModel())
					.fireTableDataChanged();
		}
	}

}
