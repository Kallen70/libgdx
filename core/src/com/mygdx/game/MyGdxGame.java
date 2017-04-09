package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture m;

	float x, y, xv, yv;
	boolean stand, jump = true;
	Animation walk;
	float time;
	static final float MAX_VELOCITY = 500;
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	static final int DRAW_WIDTH = WIDTH * 5;
	static final int DRAW_HEIGHT = HEIGHT * 5;
	static final float MAX_JUMP_VELOCITY = 2000;
	//static final int GRAVITY = -50;

	TextureRegion down, up, right, left;
	boolean faceRight, faceLeft, faceUp, faceDown;


	@Override
	public void create() {
		batch = new SpriteBatch();
		m = new Texture("tiles.png");
		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
		down = grid[6][0];
		up = grid[6][1];
		right = grid[6][3];
		left = new TextureRegion(right);
		left.flip(true, false);
		walk = new Animation(0.2f, grid[6][0], grid[6][1], grid[6][3]);
	}

	@Override
	public void render() {

	move();

		Gdx.gl.glClearColor(0, 2, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (faceRight) {
			batch.draw(right, x, y, DRAW_WIDTH, DRAW_HEIGHT);
		} else if (faceLeft) {
			batch.draw(left, x, y, DRAW_WIDTH, DRAW_HEIGHT);

		} else if (faceUp)
			batch.draw(up, x, y, DRAW_WIDTH, DRAW_HEIGHT);

		else

			batch.draw(down, x, y, DRAW_WIDTH, DRAW_HEIGHT);


		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		m.dispose();

	}

	float decelerate(float velocity) {
		float deceleration = 0.95f; // the closer to 1, the slower the deceleration
		velocity *= deceleration;
		if (Math.abs(velocity) < 1) {
			velocity = 0;
		}
		return velocity;


	}

	void move() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = MAX_VELOCITY;
			faceUp = true;
			faceRight = false;
			faceLeft = false;
			faceRight = false;

		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -1;
			faceDown = true;
			faceRight = false;
			faceUp = false;
			faceLeft = false;


		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv= MAX_VELOCITY;
			faceRight = true;
			faceLeft = false;
			faceUp = false;
			faceDown = false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_VELOCITY * -1;
			faceLeft = true;
			faceRight = false;
			faceUp = false;
			faceDown = false;
		}


		y += yv * Gdx.graphics.getDeltaTime();
		x += xv * Gdx.graphics.getDeltaTime();

		if (y < 0) {
			y = 300;


		} else if (y > 300) {
			y = 0;

		} else if (x < 0) {
			x = 350;

		} else if (x > 400) {
			x = 0;
		}


		yv = decelerate(yv);
		xv = decelerate(xv);

	}


}