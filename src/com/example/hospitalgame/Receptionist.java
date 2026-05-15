package com.example.hospitalgame;

import android.graphics.Bitmap;

/**
 * Receptionist - Main playable character (replaces Mario)
 * Professional hotel worker managing the front desk operations
 */
public class Receptionist {
    
    public static final int WIDTH = 32;
    public static final int HEIGHT = 48;
    public static final int RUN_SPEED = 5;
    public static final int JUMP_POWER = 20;
    
    private float x, y;
    private float velocityX = 0;
    private float velocityY = 0;
    private int health = 100;
    private int serviceRating = 0; // Tips and ratings earned
    private boolean isJumping = false;
    private boolean isProtected = false;
    private long protectionEndTime = 0;
    private Bitmap sprite;
    
    public Receptionist(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }
    
    /**
     * Move receptionist left
     */
    public void moveLeft() {
        velocityX = -RUN_SPEED;
    }
    
    /**
     * Move receptionist right
     */
    public void moveRight() {
        velocityX = RUN_SPEED;
    }
    
    /**
     * Stop horizontal movement
     */
    public void stopMovement() {
        velocityX = 0;
    }
    
    /**
     * Make receptionist jump
     */
    public void jump() {
        if (!isJumping) {
            velocityY = -JUMP_POWER;
            isJumping = true;
        }
    }
    
    /**
     * Apply gravity and update position
     */
    public void update(float gravity) {
        // Apply gravity
        velocityY += gravity;
        
        // Update position
        x += velocityX;
        y += velocityY;
        
        // Check if touching ground (y position based on game)
        if (y >= 400) { // Example ground level
            y = 400;
            isJumping = false;
            velocityY = 0;
        }
        
        // Update protection status
        if (isProtected && System.currentTimeMillis() > protectionEndTime) {
            isProtected = false;
        }
    }
    
    /**
     * Activate luxury 5-star protection
     */
    public void activateLuxuryProtection() {
        isProtected = true;
        protectionEndTime = System.currentTimeMillis() + 10000; // 10 seconds
        serviceRating += 500; // Bonus points for getting 5-star
    }
    
    /**
     * Take damage from guests or manager
     */
    public void takeDamage(int damage) {
        if (!isProtected) {
            health -= damage;
            if (health < 0) health = 0;
        }
    }
    
    /**
     * Earn tips/service rating
     */
    public void earnTip(int amount) {
        serviceRating += amount;
    }
    
    /**
     * Recover health (from restaurant bonus ticket)
     */
    public void recoverHealth(int amount) {
        health += amount;
        if (health > 100) health = 100;
    }
    
    /**
     * Check collision with another game object
     */
    public boolean collidesWith(float otherX, float otherY, int otherWidth, int otherHeight) {
        return (x < otherX + otherWidth &&
                x + WIDTH > otherX &&
                y < otherY + otherHeight &&
                y + HEIGHT > otherY);
    }
    
    // Getters and Setters
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    
    public int getHealth() { return health; }
    public int getServiceRating() { return serviceRating; }
    public boolean isProtected() { return isProtected; }
    public boolean isJumping() { return isJumping; }
    public void setJumping(boolean jumping) { isJumping = jumping; }
    
    public long getProtectionTimeRemaining() {
        if (!isProtected) return 0;
        long remaining = protectionEndTime - System.currentTimeMillis();
        return remaining > 0 ? remaining : 0;
    }
}
