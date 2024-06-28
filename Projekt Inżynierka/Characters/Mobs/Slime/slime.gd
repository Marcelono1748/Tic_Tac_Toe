extends CharacterBody2D



var radius = 30

var angle = 0

var stats = null
var blink = null
var action = null
var plstats = null
enum{
	Neutral,
	Attack,
	Death
}

func _ready():
	action = Neutral
	stats = $NPCStats
	blink = $Blink
	plstats = PlayerStats
func _physics_process(_delta):
	
	match action:
		Neutral:
			velocity = Vector2.ZERO
		Attack:
			Follow()
		Death:
			RIP()
	move_and_slide()
	
func _on_hurtbox_area_entered(_area):
	if stats.hp > 0:
		blink.blink()
	stats.hp = stats.hp - _area.damage

func _on_stats_no_hp():
	action = Death
	

func _on_blink_hurtbox_disable():
	$hurtbox/CollisionShape2D.set_deferred("disabled",true)
	set_collision_mask_value(4,false)
	stats.spd = stats.slowed
	


func _on_blink_hurtbox_enable():
	$hurtbox/CollisionShape2D.set_deferred("disabled",false)
	set_collision_mask_value(4,true)
	stats.spd = stats.Basespd


func _on_blink_sprite_visible_true():
	$Sprite2D.visible = true

func _on_blink_sprite_visible_false():
	$Sprite2D.visible = false
	


func _on_detection_range_area_entered(area):
	action = Attack

func _on_detection_range_area_exited(area):
	action = Neutral
	print("mamussy")
func Follow():
	var PlayerPosition = plstats.Location
	var dir = (PlayerPosition - global_position).normalized()
	velocity = velocity.move_toward(dir*stats.spd, stats.spd)

func RIP():
	stats.spd = 0
	velocity = Vector2.ZERO
	await get_tree().create_timer(0.5).timeout
	queue_free()

