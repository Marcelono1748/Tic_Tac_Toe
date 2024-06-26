extends Node

@export var Basespd = 1
@export var Basehp = 1
@export var Baseslow = 0.5
@export var BaseCarrotRate = 0.5
@export var BaseBananaRate = 0.5

@onready var spd = Basespd
@onready var slow = Baseslow
@onready var slowed = spd*slow
@onready var CarrotRate = BaseCarrotRate
@onready var BananaRate = BaseBananaRate
@onready var hp = Basehp : set = hpChange

const Carrot_Projectile = preload("res://Objects/Carrot_Projectile.tscn")
const Banana_Projectile = preload("res://Objects/Banana_Projectile.tscn")

const Carrot_Sprite = preload("res://Textures/Objects/Carrot.png")
const Banana_Sprite = preload("res://Textures/Objects/banana.png")

signal No_hp
signal hpFlux(val)

func hpChange(val):
	hp = val
	emit_signal("hpFlux", hp)
	if hp <= 0:
		emit_signal("No_hp")
