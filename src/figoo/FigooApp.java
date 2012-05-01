// fstab
// /dev/sdb* /var/tmp/usb-static-path ext4 or ntfs-3g, ro,defaults,umask=....
// 
package figoo;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class FigooApp extends SingleFrameApplication {

  /**
   * At startup create and show the main frame of the application.
   */
  @Override
  protected void startup() {
    show(new FigooView(this));
  }

  /**
   * This method is to initialize the specified window by injecting resources.
   * Windows shown in our application come fully initialized from the GUI
   * builder, so this additional configuration is not needed.
   *
   * @param root
   */
  @Override
  protected void configureWindow(java.awt.Window root) {
  }

  /**
   * A convenient static getter for the application instance.
   *
   * @return the instance of FigooApp
   */
  public static FigooApp getApplication() {
    return Application.getInstance(FigooApp.class);
  }

  /**
   * Main method launching the application.
   *
   * @param args
   */
  public static void main(String[] args) {
    launch(FigooApp.class, args);
  }
}
