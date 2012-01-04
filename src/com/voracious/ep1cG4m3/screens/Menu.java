/*  
 *  Ep1c G4m3 -- A parody platformer
 * 
 *  Copyright (C) 2011  Voracious Softworks
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */

package com.voracious.ep1cG4m3.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.voracious.ep1cG4m3.framework.Drawable;
import com.voracious.ep1cG4m3.framework.Screen;
import com.voracious.ep1cG4m3.utils.ScreenResultListener;
import com.voracious.ep1cG4m3.utils.Text;

/**
 * First thing the user sees. This class offers navigation to other screens. Such as play, instructions, and level creator.
 * 
 * @author Voracious Softworks
 * @version 6/21/2011
 */

public class Menu extends Screen {

	/**
	 * Generated by eclipse
	 */
	private static final long serialVersionUID = -6714148837311313303L;
	public static final int RESULT_PLAY = 0; //The result codes are indexes in the gui ArrayList
	public static final int RESULT_INSTRUCTIONS = 2;
	public static final int RESULT_LEVEL_EDITOR = 4;

	private Mouse mouseListener;
	private ArrayList<Drawable> gui;

	/**
	 * Sets id to be returned for identifying in the calling class and the class that will be returned to.
	 * 
	 * @param id unique screen id
	 * @param listener method that the screen will return a result to.
	 * @see Screen
	 */

	public Menu(ScreenResultListener listener, int id) {
		super(listener, id);
		mouseListener = new Mouse();
		gui = new ArrayList<Drawable>();
	}

	/**
	 * @see Screen
	 */

	@Override
	public void start() {
		addMouseListener(mouseListener);

		/*
		 * The button that was clicked is determined by the button's index. Do not change their order or add anything before them without changing the RESULT_[NAME] variables.
		 */

		BufferedImage temp = new BufferedImage(400, 75, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = temp.createGraphics();
		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, 400, 75);
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, 399, 74);
		gui.add(new Drawable(temp, new Point(50, 50)));
		gui.add(new Text("Start", new Point(60, 80), 15, 0, Color.BLACK));

		temp = new BufferedImage(400, 75, BufferedImage.TYPE_INT_RGB);
		g2 = temp.createGraphics();
		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, 400, 75);
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, 399, 74);
		gui.add(new Drawable(temp, new Point(50, 145)));
		gui.add(new Text("Instructions", new Point(60, 175), 15, 0, Color.BLACK));

		temp = new BufferedImage(400, 75, BufferedImage.TYPE_INT_RGB);
		g2 = temp.createGraphics();
		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, 400, 75);
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, 399, 74);
		gui.add(new Drawable(temp, new Point(50, 240)));
		gui.add(new Text("Level Editor", new Point(60, 270), 15, 0, Color.BLACK));

		gui.add(new Text("Menu", new Point(5, 10), 25, 0, Color.BLACK));
	}

	/**
	 * @see Screen
	 */

	@Override
	public void stop() {
		removeMouseListener(mouseListener);
		for (int i = 0; i < gui.size(); i++)
			gui.remove(i);
	}

	/**
	 * @see Screen
	 */

	@Override
	public void draw(Graphics g) {
		for (Drawable o : gui) {
			o.paintIcon(this, g);
		}
	}

	class Mouse extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			for (int i = 0; i < gui.size(); i++) {
				Point loc = gui.get(i).getLocation();
				BufferedImage img = (BufferedImage) gui.get(i).getImage();

				if (x > loc.getX() && y > loc.getY() && x < loc.getX() + img.getWidth() && y < loc.getY() + img.getHeight()) {
					if (i == RESULT_PLAY || i == RESULT_INSTRUCTIONS || i == RESULT_LEVEL_EDITOR) {
						dispatchResult(i);
						break;
					}
				}
			}
		}
	}

	/**
	 * @see Screen
	 */

	@Override
	public void update() {
		//Nothing for now, maybe an animated background later.
	}
}
