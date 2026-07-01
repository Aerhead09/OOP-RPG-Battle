CREATE TABLE enemy (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,

    hp INTEGER NOT NULL,
    atk INTEGER NOT NULL,
    def INTEGER NOT NULL,
    matk INTEGER NOT NULL,
    mdef INTEGER NOT NULL,
    agi INTEGER NOT NULL,
    luck INTEGER NOT NULL,

    element TEXT NOT NULL
);

CREATE TABLE skill (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,

    type TEXT NOT NULL,           -- PHYSICAL / MAGIC / HEAL / BUFF
    element TEXT NOT NULL,        -- FIRE / WATER / THUNDER / NONE

    power INTEGER NOT NULL,
    mana_cost INTEGER NOT NULL,

    status_effect TEXT,           -- BURN / POISON / STUN / NULL
    effect_chance INTEGER DEFAULT 0,
    effect_duration INTEGER DEFAULT 0
);

CREATE TABLE skill_tree (
    id INTEGER PRIMARY KEY AUTOINCREMENT,

    skill_id INTEGER NOT NULL,

    required_level INTEGER NOT NULL,
    required_skill_id INTEGER,   -- nullable (root skill)

    job_type TEXT NOT NULL,

    FOREIGN KEY(skill_id) REFERENCES skill(id),
    FOREIGN KEY(required_skill_id) REFERENCES skill(id)
);

CREATE TABLE item (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,

    type TEXT NOT NULL,           -- CONSUMABLE / KEYITEM / EQUIPMENT
    description TEXT,

    buy_price INTEGER DEFAULT 0,
    sell_price INTEGER DEFAULT 0
);

CREATE TABLE equipment (
    item_id INTEGER PRIMARY KEY,

    type TEXT NOT NULL,           -- WEAPON / ARMOR / ACCESSORY

    atk_bonus INTEGER DEFAULT 0,
    def_bonus INTEGER DEFAULT 0,
    matk_bonus INTEGER DEFAULT 0,
    mdef_bonus INTEGER DEFAULT 0,
    agi_bonus INTEGER DEFAULT 0,
    luck_bonus INTEGER DEFAULT 0,

    max_hp_bonus INTEGER DEFAULT 0,

    FOREIGN KEY(item_id) REFERENCES item(id)
);

CREATE TABLE consumable (
    item_id INTEGER PRIMARY KEY,

    heal_hp INTEGER DEFAULT 0,
    heal_mp INTEGER DEFAULT 0,
    is_revive INTEGER DEFAULT 0,

    FOREIGN KEY(item_id) REFERENCES item(id)
);

CREATE TABLE save_game (
    id INTEGER PRIMARY KEY AUTOINCREMENT,

    player_name TEXT NOT NULL,

    level INTEGER DEFAULT 1,
    exp INTEGER DEFAULT 0,

    hp INTEGER,
    mp INTEGER,

    floor INTEGER DEFAULT 1,
    gold INTEGER DEFAULT 0
);

CREATE TABLE save_inventory (
    id INTEGER PRIMARY KEY AUTOINCREMENT,

    save_id INTEGER NOT NULL,
    item_id INTEGER NOT NULL,
    quantity INTEGER DEFAULT 1,

    FOREIGN KEY(save_id) REFERENCES save_game(id),
    FOREIGN KEY(item_id) REFERENCES item(id)
);

CREATE TABLE save_skill (
    id INTEGER PRIMARY KEY AUTOINCREMENT,

    save_id INTEGER NOT NULL,
    skill_id INTEGER NOT NULL,

    FOREIGN KEY(save_id) REFERENCES save_game(id),
    FOREIGN KEY(skill_id) REFERENCES skill(id)
);

CREATE TABLE save_map (
    id INTEGER PRIMARY KEY AUTOINCREMENT,

    save_id INTEGER NOT NULL,

    current_floor INTEGER DEFAULT 1,
    last_x INTEGER DEFAULT 0,
    last_y INTEGER DEFAULT 0,

    FOREIGN KEY(save_id) REFERENCES save_game(id)
);