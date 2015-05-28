package org.gokapp.proxy.server.ui.simpleui.events;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Window event adaptor for Smart Proxy
 * 
 * @author grangarajan
 *
 */
public class AdaptiveWindowEvent extends WindowAdapter {

	/**
	 * Fixed thread pool
	 */
	private final static ExecutorService service;

	static {
		service = Executors.newFixedThreadPool(5);
	}

	/**
	 * Holds what to call on window activated
	 */
	private Callable<Integer> windowActivatedAction;
	/**
	 * Holds what to call on window closed
	 */
	private Callable<Integer> windowClosedAction;
	/**
	 * Holds what to call while window is closing
	 */
	private Callable<Integer> windowClosingAction
	/**
	 * Holds what to call on window is deactivated
	 */
	;
	private Callable<Integer> windowDeactivatedAction;
	/**
	 * Holds what to call on window is deiconized
	 */
	private Callable<Integer> windowDeiconifiedAction;
	/**
	 * Holds what to call on window focus
	 */
	private Callable<Integer> windowGainedFocusAction;
	/**
	 * Holds what to call on window iconized
	 */
	private Callable<Integer> windowIconifiedAction;
	/**
	 * Holds what to call on window focus lost
	 */
	private Callable<Integer> windowLostFocusAction;
	/**
	 * Holds what to call on window open
	 */
	private Callable<Integer> windowOpenedAction;
	/**
	 * Holds what to call on window state change
	 */
	private Callable<Integer> windowStateChangedAction;

	/**
	 * @param windowActivatedAction
	 *            the windowActivatedAction to set
	 */
	public void setWindowActivatedAction(Callable<Integer> windowActivatedAction) {
		this.windowActivatedAction = windowActivatedAction;
	}

	/**
	 * @param windowClosedAction
	 *            the windowClosedAction to set
	 */
	public void setWindowClosedAction(Callable<Integer> windowClosedAction) {
		this.windowClosedAction = windowClosedAction;
	}

	/**
	 * @param windowClosingAction
	 *            the windowClosingAction to set
	 */
	public void setWindowClosingAction(Callable<Integer> windowClosingAction) {
		this.windowClosingAction = windowClosingAction;
	}

	/**
	 * @param windowDeactivatedAction
	 *            the windowDeactivatedAction to set
	 */
	public void setWindowDeactivatedAction(
			Callable<Integer> windowDeactivatedAction) {
		this.windowDeactivatedAction = windowDeactivatedAction;
	}

	/**
	 * @param windowDeiconifiedAction
	 *            the windowDeiconifiedAction to set
	 */
	public void setWindowDeiconifiedAction(
			Callable<Integer> windowDeiconifiedAction) {
		this.windowDeiconifiedAction = windowDeiconifiedAction;
	}

	/**
	 * @param windowGainedFocusAction
	 *            the windowGainedFocusAction to set
	 */
	public void setWindowGainedFocusAction(
			Callable<Integer> windowGainedFocusAction) {
		this.windowGainedFocusAction = windowGainedFocusAction;
	}

	/**
	 * @param windowIconifiedAction
	 *            the windowIconifiedAction to set
	 */
	public void setWindowIconifiedAction(Callable<Integer> windowIconifiedAction) {
		this.windowIconifiedAction = windowIconifiedAction;
	}

	/**
	 * @param windowLostFocusAction
	 *            the windowLostFocusAction to set
	 */
	public void setWindowLostFocusAction(Callable<Integer> windowLostFocusAction) {
		this.windowLostFocusAction = windowLostFocusAction;
	}

	/**
	 * @param windowOpenedAction
	 *            the windowOpenedAction to set
	 */
	public void setWindowOpenedAction(Callable<Integer> windowOpenedAction) {
		this.windowOpenedAction = windowOpenedAction;
	}

	/**
	 * @param windowStateChangedAction
	 *            the windowStateChangedAction to set
	 */
	public void setWindowStateChangedAction(
			Callable<Integer> windowStateChangedAction) {
		this.windowStateChangedAction = windowStateChangedAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		super.windowActivated(e);
		scheduleAction(windowActivatedAction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		scheduleAction(windowClosedAction);
		super.windowClosed(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		scheduleAction(windowClosingAction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowDeactivated(java.awt.event.WindowEvent
	 * )
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		super.windowDeactivated(e);
		scheduleAction(windowDeactivatedAction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowDeiconified(java.awt.event.WindowEvent
	 * )
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		super.windowDeiconified(e);
		scheduleAction(windowDeiconifiedAction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowGainedFocus(java.awt.event.WindowEvent
	 * )
	 */
	@Override
	public void windowGainedFocus(WindowEvent e) {
		super.windowGainedFocus(e);
		scheduleAction(windowGainedFocusAction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		super.windowIconified(e);
		scheduleAction(windowIconifiedAction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowLostFocus(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowLostFocus(WindowEvent e) {
		super.windowLostFocus(e);
		scheduleAction(windowLostFocusAction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		scheduleAction(windowOpenedAction);
		super.windowOpened(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowStateChanged(java.awt.event.WindowEvent
	 * )
	 */
	@Override
	public void windowStateChanged(WindowEvent e) {
		super.windowStateChanged(e);
		scheduleAction(windowStateChangedAction);
	}

	/**
	 * Schedules action that is performed during the event
	 * 
	 * @param action
	 * @return action result or -1 if exception occurs
	 * 
	 */
	private int scheduleAction(Callable<Integer> action) {
		if (action == null) {
			return 0;
		}
		try {
			return service.submit(action).get();
		} catch (InterruptedException e) {
			// TODO Log result
			e.printStackTrace();
			return -1;
		} catch (ExecutionException e) {
			// TODO Log result
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Stop the executer service
	 */
	public static void shutdownService() {
		if (null != service) {
			service.shutdown();
		}
	}

}
