extends Node

@export var Basespd = 1
@export var Basehp = 1
@export var Baseslow = 0.5


@onready var spd = Basespd
@onready var slow = Baseslow
@onready var slowed = spd*slow
@onready var hp = Basehp : set = hpChange
signal No_hp
signal hpFlux(val)
signal hpSend(val)
func _ready():
	emit_signal("hpSend", hp)
func hpChange(val):
	hp = val
	emit_signal("hpFlux", hp)
	if hp <= 0:
		emit_signal("No_hp")
