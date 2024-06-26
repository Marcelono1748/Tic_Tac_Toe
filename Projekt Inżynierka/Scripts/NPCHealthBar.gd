extends Control
var Basehealth = 1 : set = set_Basehealth



var HealthBar = null
var DamageBar = null
var HealthChunk = null

signal DamageBarSignal
func _ready():
	HealthBar = $ActualHealthBar
	DamageBar = $ActualHealthBar/DamageBar
	HealthChunk = HealthBar.max_value / Basehealth
	HealthBar.value = HealthBar.max_value
	DamageBar.value = HealthBar.value


func set_Basehealth(val):
	Basehealth = max(val,1)


func _on_npc_stats_hp_flux(val):
	HealthBar.visible = true
	HealthBar.value = HealthChunk * val
	await get_tree().create_timer(0.35).timeout
	DamageBar.value = HealthBar.value
		
	
func _on_npc_stats_hp_send(val):
	#self.
	Basehealth = val
