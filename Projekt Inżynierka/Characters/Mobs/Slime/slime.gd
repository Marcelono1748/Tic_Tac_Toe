extends CharacterBody2D



var radius = 30

var angle = 0

var stats = null
var blink = null
var action = null
enum{
	Neutral,
	Attack
}

func _ready():
	action = Neutral
	stats = $NPCStats
	blink = $Blink
func _physics_process(_delta):
	var invec = Vector2.ZERO
	match action:
		Neutral:
			print("A mimir")
		Attack:
			print("PitbullModeActivated")
	
func _on_hurtbox_area_entered(_area):
	if stats.hp > 0:
		blink.blink()
	stats.hp = stats.hp - _area.damage

func _on_stats_no_hp():
	stats.spd = 0
	await get_tree().create_timer(0.5).timeout
	queue_free()

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


