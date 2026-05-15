package com.example.hospitalgame;

/**
 * Guest - Regular enemy (replaces Goomba)
 * Hotel guests that patrol the hallways
 */
public class Guest {
    
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;
    public static final int PATROL_SPEED = 2;
    public static final int DIRECTION_CHANGE_INTERVAL = 200;
    public static final int DAMAGE = 10;
    
    private float x, y;
    private int direction = 1;
    private boolean isAngry = false;
    private long lastDirectionChange = System.currentTimeMillis();
    private float velocityX = 0;
    private float velocityY = 0;
    
    public Guest(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }
    
    /**
     * Update guest patrol behavior
     */
    public void update(long currentTime) {
        velocityX = direction * PATROL_SPEED;
        
        if (currentTime - lastDirectionChange > DIRECTION_CHANGE_INTERVAL) {
            if (Math.random() > 0.7) {
                reverseDirection();
                lastDirectionChange = currentTime;
            }
        }
        
        x += velocityX;
        y += velocityY;
    }
    
    /**
     * Reverse direction of patrol
     */
    public void reverseDirection() {
        direction *= -1;
        velocityX = direction * PATROL_SPEED;
    }
    
    /**
     * Handle collision with receptionist
     */
    public void collideWithReceptionist(Receptionist receptionist) {
        if (receptionist != null) {
            if (receptionist.isProtected()) {
                isAngry = true;
                reverseDirection();
            } else {
                receptionist.takeDamage(DAMAGE);
                isAngry = true;
                reverseDirection();
            }
        }
    }
    
    /**
     * Check collision with receptionist
     */
    public boolean collidesWithReceptionist(Receptionist receptionist) {
        if (receptionist == null) return false;
        
        return (x < receptionist.getX() + Receptionist.WIDTH &&
                x + WIDTH > receptionist.getX() &&
                y < receptionist.getY() + Receptionist.HEIGHT &&
                y + HEIGHT > receptionist.getY());
    }
    
    /**
     * Calm down the guest
     */
    public void calmDown() {
        isAngry = false;
    }
    
    /**
     * Get guest status
     */
    public String getStatus() {
        return isAngry ? "Angry Guest" : "Guest Walking";
    }
    
    // Getters and Setters
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    
    public int getDirection() { return direction; }
    public void setDirection(int dir) { direction = (dir > 0) ? 1 : -1; }
    
    public boolean isAngry() { return isAngry; }
    public void setAngry(boolean angry) { isAngry = angry; }
    
    public float getVelocityX() { return velocityX; }
    public void setVelocityX(float vx) { velocityX = vx; }
}
