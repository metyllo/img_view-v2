
package javaapplication8;

/**
 * @author  Krystian Kurzawa 
 * writen on Yasir Feroze Minhas example from http://www.developer.com/ 
 * also used http://www.eclipse.org/articles/Article-Image-Viewer/Image_viewer.html?PHPSESSID=1ed995827b59c704397a3cf1250f694d
 * as information and tutorial page.
 */

import java.awt.*;
import java.awt.event.*;
public class ImageBrowser extends Frame {

  //private variables to hold image and current directory name.
  private Image m = null;
  private String dir = null;


  //contants for Control-B and Control-X keys
  private static final int kControlB = 66;
  private static final int kControlX = 88;

  public static void main(String[] arg){
    ImageBrowser browser = new ImageBrowser();
    browser.setVisible(true);
  }

  public ImageBrowser() {
    try  {
      Init();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  private void Init() throws Exception {

    //set title, background color and size of frame
    this.setTitle("Image Browser");
    this.setBackground(Color.darkGray);
    this.setSize(200,200);
    //create menubar for this frame
    MenuBar menuBar = new MenuBar();
    //attach image menu with menubar
    Menu menu = new Menu("Image");
    //include options in image menu
    menu.add(new MenuItem("Browse Image", new MenuShortcut(kControlB)));
    menu.add(new MenuItem("Quit", new MenuShortcut(kControlX)));
    menuBar.add(menu);
    //listen to the actions user performs on menu
    menu.addActionListener(new axnListener());
    this.setMenuBar(menuBar);
    //listen to window actions with anonymous inner class
    this.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e)
      {
        System.exit(0);
      }
    });
  }
  //opens a file dialog in LOAD mode and retrieves image file name
  public void loadImage()
  {
    FileDialog dlg = new FileDialog(this, "Choose Image", FileDialog.LOAD);
    //set current directory
    if(dir != null){
      dlg.setDirectory(dir);
    }
    dlg.setVisible(true);
    //get image name and path
    String imgFile = dlg.getDirectory()+dlg.getFile();
    dir = dlg.getDirectory();
    //create image using filename
    Toolkit tk = Toolkit.getDefaultToolkit();
    m = tk.getImage(imgFile);
    //call repaint to draw image
    repaint();
  }
  public void paint(Graphics g){
    //if image is not null, draw it and set the size of frame
    //according to the size of image
    if(m != null)
    {
      //The size of the frame includes any area designated for the border.
      //The dimensions of the border area can be obtained
      //using the getInsets method
      Insets insets = this.getInsets();
      g.drawImage(m, insets.left, insets.top, this);
      this.setSize(m.getWidth(this)+insets.left, m.getHeight(this)+insets.top);
    }
  }

  //inner class to listen menu actions
  class axnListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(e.getActionCommand().equalsIgnoreCase("Browse Image")){
        loadImage();
      }
      else System.exit(0);
    }
  }//end of inner class axnListener

}//end of ImageBrowser