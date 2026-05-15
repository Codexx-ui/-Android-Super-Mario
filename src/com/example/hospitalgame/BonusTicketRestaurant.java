package com.example.hospitalgame;

/**
 * BonusTicketRestaurant - Power-up (replaces Mushroom)
 * Provides health recovery and bonus points
 */
public class BonusTicketRestaurant {
    
    public static final int WIDTH = 24;
    public static final int HEIGHT = 24;
    public static final int BONUS_POINTS = 100;
    public static final int HEALTH_RECOVERY = 30;
    
    private float x, y;
    private boolean isCollected = false;
    private float bounceOffset = 0;
    private float bounceSpeed = 0.15f;
    
    public BonusTicketRestaurant(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }
    
    /**
     * Update bouncing animation
     */
    public void update() {
        if (!isCollected) {
            // Bouncing animation
            bounceOffset += bounceSpeed;
            if (bounceOffset > 1.0f || bounceOffset < -1.0f) {
                bounceSpeed *= -1;
            }
        }
    }
    
    /**
     * Apply bonus to receptionist
     */
    public void applyBonus(Receptionist receptionist) {
        if (!isCollected && receptionist != null) {
            receptionist.recoverHealth(HEALTH_RECOVERY);
            receptionist.earnTip(BONUS_POINTS);
            isCollected = true;
        }
    }
    
    /**
     * Check collision with receptionist
     */
    public boolean collidesWithReceptionist(Receptionist receptionist) {
        if (isCollected || receptionist == null) return false;
        
        return (x < receptionist.getX() + Receptionist.WIDTH &&
                x + WIDTH > receptionist.getX() &&
                y < receptionist.getY() + Receptionist.HEIGHT &&
                y + HEIGHT > receptionist.getY());
    }
    
    /**
     * Get description
     */
    public String getDescription() {
        return "🎫 Bonus Restaurant Ticket - +" + HEALTH_RECOVERY + " health, +" + BONUS_POINTS + " points";
    }
    
    /**
     * Get visual Y position with bouncing effect
     */
    public float getVisualY() {
        return y + bounceOffset * 8; // Bounces up and down 8 pixels
    }
    
    // Getters and Setters
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    
    public boolean isCollected() { return isCollected; }
    public void setCollected(boolean collected) { isCollected = collected; }
}
