# Hospitality Super Mario - Transformation Package

## 📋 Quick Reference

### Character & Item Transformations

```
MARIO              →  RECEPTIONIST       (Main Player)
GOOMBA             →  GUEST              (Regular Enemy)
PIRANHA PLANT      →  EVIL MANAGER       (Boss Enemy)
STAR POWER-UP      →  LUXURY 5-STAR      (Protection Power-up)
MUSHROOM POWER-UP  →  BONUS TICKET       (Health/Points Power-up)
```

## 📚 Classes Created

### 1. **Receptionist.java**
Main playable character replacing Mario
- Professional hotel worker
- Standard movement, jumping mechanics
- Health and Service Rating system
- Protection state management

**Key Methods:**
- `moveLeft()` / `moveRight()` - Navigation
- `jump()` - Jump mechanic
- `activateLuxuryProtection()` - Activate 5-star protection
- `earnTip(int amount)` - Gain service points
- `takeDamage(int damage)` - Take damage from enemies

### 2. **Guest.java**
Regular enemy replacing Goomba
- Patrols hotel hallways back and forth
- Becomes "angry" when colliding with unprotected receptionist
- Deals 10 damage on collision
- Reverses direction when handled with protection

**Key Methods:**
- `update(long currentTime)` - Behavior logic
- `reverseDirection()` - Turn around
- `collideWithReceptionist()` - Handle collision
- `isAngry()` - Get state

### 3. **EvilManager.java**
Boss enemy replacing Piranha Plant
- Actively chases receptionist when detected
- 200px detection range
- Deals 25 damage on collision
- Backs off when receptionist has protection

**Key Methods:**
- `update(Receptionist receptionist)` - Chase behavior
- `chaseReceptionist()` - Pursuit logic
- `patrol()` - Idle movement
- `isChasing()` - Get state
- `collideWithReceptionist()` - Boss collision handling

### 4. **LuxuryFiveStar.java**
Power-up replacing Star (Invincibility)
- Grants 10 seconds of protection
- Floating animation (bobbing up and down)
- Adds 500 bonus service points on collection
- Protects from both guests and evil manager

**Key Methods:**
- `activateOn(Receptionist receptionist)` - Apply bonus
- `getProtectionDurationRemaining()` - Get time left
- `isProtectionExpired()` - Check expiration
- `getDescription()` - Get power-up info

### 5. **BonusTicketRestaurant.java**
Power-up replacing Mushroom (Health)
- Awards 100 service points
- Recovers 30 health
- Simple bounce animation
- Tracks collection state

**Key Methods:**
- `applyBonus(Receptionist receptionist)` - Apply reward
- `isCollected()` - Check state
- `getDescription()` - Get power-up info

## 🎮 Game Mechanics Changes

| Element | Original | New |
|---------|----------|-----|
| **Score** | Coins collected | Service Rating/Tips |
| **Lives** | Extra lives | Health/Reputation |
| **Level Objective** | Reach castle | Reach manager's office or complete daily task |
| **Power Protection** | Star = invincible | 5-Star Rating = protected for 10 seconds |
| **Health Boost** | Mushroom = size up | Restaurant Ticket = +30 health |
| **Enemy Behavior** | Fixed patterns | Guests patrol, Manager chases |

## 🎨 Sprite/Asset Changes Needed

Replace these assets with hospitality-themed versions:

```
mario.png              →  receptionist.png          (wearing hotel uniform)
goomba.png             →  guest.png                 (customer appearance)
piranha_plant.png      →  evil_manager.png          (stern manager)
star.png               →  luxury_five_star.png      (⭐ 5-star rating)
mushroom.png           →  bonus_ticket_restaurant.png (restaurant ticket)
mario_small.png        →  receptionist_small.png    (alternate sizes)
```

## 📱 Integration Steps

1. **Replace sprites** in drawable/asset folders
2. **Update game logic** to use new classes
3. **Modify collision detection** in game loop
4. **Update UI** to show Service Rating instead of Score
5. **Update text strings** for hospitality theme
6. **Test gameplay** with new enemy behaviors

## ⚙️ Configuration Values

```java
// Receptionist
Receptionist.WIDTH = 32
Receptionist.RUN_SPEED = 5
Receptionist.JUMP_POWER = 20

// Guest
Guest.PATROL_SPEED = 2
Guest.DIRECTION_CHANGE_TIME = 200ms
Guest.DAMAGE = 10

// EvilManager
EvilManager.PATROL_SPEED = 3
EvilManager.CHASE_SPEED = 5
EvilManager.DETECTION_RANGE = 200px
EvilManager.DAMAGE = 25

// LuxuryFiveStar
LuxuryFiveStar.PROTECTION_DURATION = 10000ms
LuxuryFiveStar.BONUS_POINTS = 500

// BonusTicketRestaurant
BonusTicketRestaurant.BONUS_POINTS = 100
BonusTicketRestaurant.HEALTH_RECOVERY = 30
```

## 🔧 Example Usage

```java
// Game initialization
Receptionist player = new Receptionist(100, 100);
Guest guest1 = new Guest(300, 200);
EvilManager manager = new EvilManager(500, 150);

// Game loop
player.update();
guest1.update(System.currentTimeMillis());
manager.update(player);

// Collision handling
if (player.collidesWith(guest1)) {
    guest1.collideWithReceptionist(player);
}

if (player.collidesWith(manager)) {
    manager.collideWithReceptionist(player);
}

// Power-up collection
if (player.collidesWith(luxuryStar)) {
    luxuryStar.activateOn(player);
}

if (player.collidesWith(restaurantTicket)) {
    restaurantTicket.applyBonus(player);
}
```

## 📝 Notes for Implementation

- All classes extend base `Character` and `Enemy` classes (existing in your project)
- Collision detection should use existing framework
- UI should display "Service Rating" instead of "Score"
- Sound effects should be replaced with hospitality theme sounds
- Consider adding different guest types (VIP, Complaining, etc.)
- Evil Manager could have additional attack patterns

## ✅ Next Steps

1. Copy all 5 Java classes into your `src/` directory
2. Update sprite assets in `res/drawable/`
3. Modify main game loop to use new classes
4. Test collision behaviors
5. Balance difficulty with new enemy stats if needed
6. Update UI text and displays

---

**Happy hotel management gaming! 🏨🎮**
