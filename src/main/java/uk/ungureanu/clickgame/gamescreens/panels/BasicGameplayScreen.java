package uk.ungureanu.clickgame.gamescreens.panels;

import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.screen.ScreenEntity;
import uk.ungureanu.clickgame.gamescreens.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.ListIterator;

public class BasicGameplayScreen extends GameplayScreen {
    private List<ScreenEntity> entities;
    private int runtime = 0;
    private JLabel runtimeLabel;
    public BasicGameplayScreen(List<ScreenEntity> entities){
        this.setLayout(null);
        this.entities = entities;
        runtimeLabel = new JLabel(Integer.toString(runtime));
        runtimeLabel.setBounds(MainScreen.getInstance().getWidth()/2-100, 10, 200, 40);
        this.add(runtimeLabel);
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                runtime+=10;
                runtimeLabel.setText(Double.toString(runtime/1000.0));
            }
        });
        timer.start();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Iterate backwards to deal with shapes drawn on top of one another
                ListIterator<ScreenEntity> iterator = entities.listIterator(entities.size());
                while(iterator.hasPrevious()) {
                    ScreenEntity entity = iterator.previous();
                    if(entity.contains(e.getPoint())) {
                        entity.onClick();
                        break;
                    }
                }
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D drawer = (Graphics2D)g;
        for (ScreenEntity e:entities) {
            g.setColor(e.getColour());
            drawer.fill(e);
        }
    }


    @Override
    public void removeElement(ScreenEntity entity) {
        ListIterator<ScreenEntity> iterator = this.entities.listIterator();
        while(iterator.hasNext()) {
            ScreenEntity nextEntity = iterator.next();
            if (nextEntity.equals(entity)) {
                iterator.remove();
                break;
            }
        }
    }
}
