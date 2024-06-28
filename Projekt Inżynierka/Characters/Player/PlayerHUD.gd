extends Control
var Basehealth = 1 : set = set_Basehealth



var HealthBar = null
var DamageBar = null
var HealthChunk = null
var stats = null
var MouseFollower = null
var FPSLabel = null
var HealthBarFadeTimer = null
signal DamageBarSignal
func _ready():
	stats = PlayerStats
	Basehealth = stats.Basehp
	
	self.stats.connect("hpFlux", _on_player_stats_hp_flux )
	HealthBar = $ActualHealthBar
	DamageBar = $ActualHealthBar/DamageBar
	MouseFollower = $MouseFollower
	FPSLabel = $FPScounter
	HealthBarFadeTimer = $ActualHealthBar/HealthBarFadeTimer
	
	HealthChunk = HealthBar.max_value / Basehealth
	HealthBar.value = HealthBar.max_value
	DamageBar.value = HealthBar.value
	
func set_Basehealth(val):
	Basehealth = max(val,1)


func _on_player_stats_hp_flux(val):
	HealthBarFadeTimer.start(0)
	HealthBar.visible = true
	HealthBar.value = HealthChunk * val
	await get_tree().create_timer(0.35).timeout
	DamageBar.value = HealthBar.value
	
	
func _physics_process(_delta):
	FPSLabel.text = "FPS: " + str(Engine.get_frames_per_second())
	MouseFollower.position = get_local_mouse_position()

func _on_health_bar_fade_timer_timeout():
	HealthBar.visible = false
