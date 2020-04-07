package plugins.interfaces;

public interface IGame {

	/**
	 */
	boolean start();

	/**
	 */
	boolean unborrow();

	/**
	 */
	boolean borrow();

	/**
	 */
	boolean uninstall();

	/**
	 *
	 */
	boolean install();

	/**
	 *
	 */
	boolean update();

	/**
	 *
	 */
	boolean download();

	/**
	 *
	 */
	boolean buy();

	/**
	 *
	 */
	boolean sell();

	/**
	 * Name of plugin
	 *
	 * @return
	 */
	String toString();


}
