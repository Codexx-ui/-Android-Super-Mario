# Hospitality Game - Theme Conversion Guide

## Complete Theme Mapping

This document outlines the transformation of the Super Mario game into a hospitality-themed hotel management game.

### Character Replacements

| Original | New | Description |
|----------|-----|-------------|
| **Mario** | **Receptionist** | Main playable character working at the hotel front desk |
| **Goomba** | **Guest** | Regular customers wandering through the hotel |
| **Piranha Plant** | **Evil Manager** | Boss enemy - the strict/demanding hotel manager |
| **Star Power-up** | **Luxury 5-Star Rating** | Grants temporary invincibility/protection |
| **Mushroom** | **Bonus Ticket Restaurant** | Bonus power-up that grants points and abilities |
| **Castle/Goal** | **Presidential Suite/Hotel Office** | End objective - reach the manager's office or complete daily tasks |

### Game Mechanics Changes

#### Gameplay Loop
- **Original**: Avoid enemies, collect coins, reach the castle
- **New**: Navigate through hotel, avoid/manage guests, collect tips and bonuses, complete service objectives

#### Scoring System
- **Coins** → **Tips/Service Points**
- **Star Power** → **Luxury Rating Bonus**
- **Mushroom** → **Restaurant Voucher/Service Bonus**

#### Collision Behavior
- **Guest Collision**: 
  - Without protection: Receptionist gets pushed back or loses health
  - With 5-Star protection: Guest is "handled" and becomes friendly
  
- **Evil Manager Collision**:
  - Immediate threat - causes significant damage
  - Requires power-up protection or evasion to survive

#### Level Design Theme
- **Platforms** → **Hotel Floors/Hallways**
- **Obstacles** → **Furniture, Room Doors, Service Carts**
- **Power-up Locations** → **Break rooms, Gift shops, Concierge desk**

### Visual Asset Changes Needed

#### Character Sprites
- `mario.png` → `receptionist.png` (professional uniform, friendlier appearance)
- `goomba.png` → `guest.png` (customer appearance, various ethnicities/styles)
- `piranha_plant.png` → `evil_manager.png` (stern-looking boss character)

#### Power-up Sprites
- `star.png` → `luxury_five_star.png` (5-star rating icon)
- `mushroom.png` → `bonus_ticket_restaurant.png` (restaurant voucher/ticket)

#### Background/Environment
- Brick blocks → Hotel room doors, decorative panels
- Clouds → Hotel lobby elements, chandeliers
- Pipes → Service entrances, elevator doors
- Flag pole → Reception desk or manager's office door

### Code Structure

New classes to implement:
- `Receptionist.java` - Main player character
- `Guest.java` - Regular enemy (replaces Goomba)
- `EvilManager.java` - Boss enemy (replaces Piranha Plant)
- `LuxuryFiveStar.java` - Power-up (replaces Star)
- `BonusTicketRestaurant.java` - Power-up (replaces Mushroom)

### UI/UX Changes
- Score → Service Rating / Tips Earned
- Lives → Hotel Reputation / Health
- Level → Floor Number or Daily Shift
- Status bar → Shows current rating and time remaining

### Story/Theme
The game is now about a hardworking receptionist trying to:
1. Manage arriving guests smoothly
2. Maintain a 5-star hotel rating
3. Avoid the strict evil manager
4. Complete daily service objectives
5. Reach the manager's office to submit daily reports

### Implementation Priority
1. **High**: Update character sprites, rename main classes
2. **High**: Update enemy behavior (Guest patrol, Evil Manager AI)
3. **Medium**: Update power-up mechanics and effects
4. **Medium**: Update UI text and scoring system
5. **Low**: Add special effects and animations for theme consistency
