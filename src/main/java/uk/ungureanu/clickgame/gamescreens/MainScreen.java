package uk.ungureanu.clickgame.gamescreens;

import uk.ungureanu.clickgame.config.BasicHardcodedConfigurator;
import uk.ungureanu.clickgame.config.Configurator;
import uk.ungureanu.clickgame.config.MainScreenConfig;
import uk.ungureanu.clickgame.gamescreens.panels.BasicNewGameScreen;
import uk.ungureanu.clickgame.gamescreens.panels.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class MainScreen {
    //Singleton, as there must be exactly one window(JFrame). Individual views are component JPanels.
    private static MainScreen screen = null;
    private JFrame frame;
    private GamePanel currentPanel;
    private Configurator gameConfig;
    private MainScreenConfig screenSettings;

    private MainScreen(Configurator configurator, MainScreenConfig settings) {
        //Can't initialise MainScreen with factory/configurator, as its constructor is private
        this.gameConfig = configurator;
        this.screenSettings = settings;
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                frame = new JFrame();
                frame.setSize(screenSettings.getWidth(), screenSettings.getHeight());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                //currentPanel = new BasicNewGameScreen();
                currentPanel = gameConfig.createNewGameScreen();
                frame.add(currentPanel);

            }
        });
    }

    public static void initialiseMainScreen(Configurator configurator,MainScreenConfig settings){
        if(MainScreen.screen == null)
            MainScreen.screen = new MainScreen(configurator, settings);
    }
    public static MainScreen getInstance(){
        if (MainScreen.screen == null) {
            //Screen not initialised with specific configurator, fallback to default hardcoded settings
            Configurator config = new BasicHardcodedConfigurator();
            MainScreen.screen = new MainScreen(config, config.getMainScreenSettings());
        }
        return MainScreen.screen;
    }

    public int getWidth(){
        return this.frame.getWidth();
    }

    public int getHeight(){
        return this.frame.getHeight();
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void removePanel() {
        this.frame.remove(screen.currentPanel);
    }

    public void setPanel(GamePanel newPanel) {
        this.removePanel();
        this.frame.add(newPanel);
        this.currentPanel = newPanel;
        Timer timer = new Timer(1000/this.screenSettings.getFramerate(), new ActionListener() {
            //Update every 1000/x ms, resulting in ~x frames per second;
            @Override
            public void actionPerformed(ActionEvent arg0) {
                newPanel.repaint();
            }
        });
        timer.start();
    }

    public Configurator getConfig() {
        return this.gameConfig;
    }


}
