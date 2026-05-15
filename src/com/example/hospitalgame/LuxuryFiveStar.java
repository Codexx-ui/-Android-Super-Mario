package com.example.hospitalgame;

/**
 * LuxuryFiveStar - Protection power-up (replaces Star)
 * Grants temporary invincibility and bonus points
 */
public class LuxuryFiveStar {
    
    public static final int WIDTH = 28;
    public static final int HEIGHT = 28;
    public static final long PROTECTION_DURATION = 10000; // 10 seconds in milliseconds
    public static final int BONUS_POINTS = 500;
    
    private float x, y;
    private boolean isCollected = false;
    private float bobOffset = 0;
    private float bobSpeed = 0.12f;
    private float rotationAngle = 0;
    
    public LuxuryFiveStar(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }
    
    /**
     * Update bobbing and rotation animation
     */
    public void update() {
        if (!isCollected) {
            // Bobbing animation (up and down)
            bobOffset += bobSpeed;
            if (bobOffset > 1.0f || bobOffset < -1.0f) {
                bobSpeed *= -1;
            }
            
            // Rotation animation
            rotationAngle += 5f;
            if (rotationAngle > 360) {
                rotationAngle = 0;
            }
        }
    }
    
    /**
     * Activate 5-star protection on receptionist
     */
    public void activateOn(Receptionist receptionist) {
        if (!isCollected && receptionist != null) {
            receptionist.activateLuxuryProtection();
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
     * Get power-up description
     */
    public String getDescription() {
        return "⭐ Luxury 5-Star Rating - " + (PROTECTION_DURATION / 1000) + "s protection, +" + BONUS_POINTS + " points";
    }
    
    /**
     * Get visual Y position with bobbing effect
     */
    public float getVisualY() {
        return y + bobOffset * 10; // Bobs up and down 10 pixels
    }
    
    /**
     * Get rotation angle for animation
     */
    public float getRotationAngle() {
        return rotationAngle;
    }
    
    /**
     * Check if protection duration is remaining
     */
    public boolean hasProtectionActive(Receptionist receptionist) {
        return receptionist != null && receptionist.isProtected();
    }
    
    // Getters and Setters
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    
    public boolean isCollected() { return isCollected; }
    public void setCollected(boolean collected) { isCollected = collected; }
}
