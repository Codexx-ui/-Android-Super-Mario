package com.example.hospitalgame;

/**
 * EvilManager - Boss enemy (replaces Piranha Plant)
 * The strict and demanding hotel manager
 */
public class EvilManager {
    
    public static final int WIDTH = 40;
    public static final int HEIGHT = 48;
    public static final int PATROL_SPEED = 3;
    public static final int CHASE_SPEED = 5;
    public static final int DETECTION_RANGE = 200; // pixels
    public static final int DAMAGE = 25; // 2.5x damage of regular guest
    
    private float x, y;
    private int direction = 1; // 1 for right, -1 for left
    private boolean isChasing = false;
    private float velocityX = 0;
    private float velocityY = 0;
    
    public EvilManager(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }
    
    /**
     * Update manager behavior - chase or patrol
     */
    public void update(Receptionist receptionist) {
        if (receptionist == null) {
            patrol();
            return;
        }
        
        // Check if receptionist is in detection range
        float distance = Math.abs(receptionist.getX() - x);
        
        if (distance < DETECTION_RANGE && !receptionist.isProtected()) {
            // Chase receptionist if not protected
            isChasing = true;
            chaseReceptionist(receptionist);
        } else {
            // Patrol if receptionist is out of range or protected
            isChasing = false;
            patrol();
        }
        
        // Update position
        x += velocityX;
        y += velocityY;
    }
    
    /**
     * Patrol behavior - move back and forth
     */
    private void patrol() {
        velocityX = direction * PATROL_SPEED;
        
        // Change direction at random intervals or at boundaries
        if (Math.random() > 0.98) { // Low probability of direction change
            direction *= -1;
        }
    }
    
    /**
     * Chase receptionist
     */
    private void chaseReceptionist(Receptionist receptionist) {
        float targetX = receptionist.getX();
        
        if (x < targetX) {
            velocityX = CHASE_SPEED;
            direction = 1;
        } else if (x > targetX) {
            velocityX = -CHASE_SPEED;
            direction = -1;
        } else {
            velocityX = 0;
        }
    }
    
    /**
     * Handle collision with receptionist
     */
    public void collideWithReceptionist(Receptionist receptionist) {
        if (receptionist != null) {
            if (receptionist.isProtected()) {
                // If receptionist is protected, back away
                velocityX = -velocityX;
                isChasing = false;
            } else {
                // Manager deals significant damage
                receptionist.takeDamage(DAMAGE);
                // Back away after collision
                velocityX = -velocityX;
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
     * Get manager status
     */
    public String getStatus() {
        return isChasing ? "😠 MANAGER ATTACKING!" : "😤 Manager on Patrol";
    }
    
    /**
     * Get AI state for debugging
     */
    public String getDebugInfo() {
        return (isChasing ? "CHASING" : "PATROLLING") + " | Speed: " + velocityX;
    }
    
    // Getters and Setters
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    
    public int getDirection() { return direction; }
    public boolean isChasing() { return isChasing; }
    public void setChasing(boolean chasing) { isChasing = chasing; }
    
    public float getVelocityX() { return velocityX; }
    public void setVelocityX(float vx) { velocityX = vx; }
}
