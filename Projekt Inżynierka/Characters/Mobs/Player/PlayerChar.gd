extends CharacterBody2D


enum{
	Movement,
	Attack
}
enum{
	Weapon0,
	Weapon1
}

var Animator = null
var AnimTree = null
var AnimStat = null
var action  = null
var stats = null
var blink = null
var CanThrow = true
var projectile = null
var WeaponType = null
var WeaponPicker = 0
var AttackRate = null
var WeaponPanelSprite = null
var AttackDelayBar = null
var AttackDelayTimer = null

var AttackTimerChunk = null

func _ready():
	stats = PlayerStats
	self.stats.connect("No_hp", _on_stats_no_hp )
	blink = $Blink
	Animator = $Player_Sprite/Player_Anims
	AnimTree = $Player_Sprite/Player_AnimationTree
	WeaponPanelSprite = $HUD/WeaponPanelSprite
	AttackDelayBar = $HUD/MouseFollower/AttackDelayBar
	AttackDelayTimer = $HUD/MouseFollower/AttackDelayBar/AttackDelayTimer
	action = Movement
	AttackDelayTimer.wait_time = stats.CarrotRate
	WeaponType = Weapon0
	projectile = stats.Carrot_Projectile
	AttackDelayBar.value = AttackDelayBar.max_value
	
	AnimStat = AnimTree.get("parameters/playback")
	AttackTimerSwitch()
	WeaponSwitch()
	
func _physics_process(_delta):
	
	match action:
		Movement:
			to_MVE()
			
		Attack:
			to_ATK()
			
	if Input.is_action_just_pressed("Attk") and CanThrow == true:
		action = Attack
		

	if Input.is_action_just_pressed("Switch Weapon"):
		WeaponSwitch()
		
	if CanThrow == false:
		AttackDelayBarUpdater()
	

func to_MVE():
	
	var invec = Vector2.ZERO
	invec.y = Input.get_action_strength("ui_down") - Input.get_action_strength("ui_up")
	invec.x = Input.get_action_strength("ui_right") - Input.get_action_strength("ui_left")
	invec=invec.normalized()
	
	if invec != Vector2.ZERO:
		AnimTree.set("parameters/Idles/blend_position", invec)
		AnimTree.set("parameters/Walks/blend_position", invec)
		AnimStat.travel("Walks")
		velocity = invec * stats.spd
	else:
		AnimStat.travel("Idles")
		velocity = Vector2.ZERO
	move_and_slide()

func to_ATK():
	var mouse = Vector2().direction_to(get_local_mouse_position().normalized())
	
	AnimStat.travel("Walks")
	AnimTree.set("parameters/Walks/blend_position", mouse)
	$Node2D.look_at(get_global_mouse_position())
	
	if CanThrow == true:
		var Throw = projectile.instantiate()
		Throw.position = $Node2D/Marker2D.global_position
		get_parent().add_child(Throw)
		Throw.velocity = get_global_mouse_position() - Throw.position
		Throw.rotation_degrees = $Node2D.rotation_degrees
		CanThrow = false
		AttackDelayBar.value = 0
		AttackDelayTimer.start()
		

	if Input.is_action_pressed("Attk") !=true: 
		await get_tree().create_timer(0.2).timeout
		action = Movement
	
func _on_hurtbox_area_entered(_area):
	if stats.hp > 0:
		blink.blink()
	stats.hp = stats.hp - _area.damage



func _on_stats_no_hp():
	stats.spd = 0
	await get_tree().create_timer(0.5).timeout
	_on_blink_sprite_visible_false()
	
	await get_tree().create_timer(1).timeout
	position.x = 100
	position.y = 120
	
	_on_blink_sprite_visible_true()
	stats.spd = stats.Basespd
	stats.hp = stats.Basehp


func _on_blink_hurtbox_disable():
	$hurtbox/CollisionShape2D.set_deferred("disabled",true)
	set_collision_mask_value(4,false)
	stats.spd = stats.slowed
	


func _on_blink_hurtbox_enable():
	$hurtbox/CollisionShape2D.set_deferred("disabled",false)
	set_collision_mask_value(4,true)
	stats.spd = stats.Basespd


func _on_blink_sprite_visible_true():
	$Player_Sprite.visible = true

func _on_blink_sprite_visible_false():
	$Player_Sprite.visible = false
	
func WeaponCarrot():
	AttackDelayTimer.wait_time = stats.CarrotRate
	projectile = stats.Carrot_Projectile
	WeaponPanelSprite.texture = stats.Carrot_Sprite
	

func WeaponBanana():
	AttackDelayTimer.wait_time = stats.BananaRate
	projectile = stats.Banana_Projectile
	WeaponPanelSprite.texture = stats.Banana_Sprite
	
	

func WeaponSwitch():
	match WeaponType:
		Weapon0:
			WeaponCarrot()
		Weapon1:
			WeaponBanana()
	WeaponPicker = WeaponPicker + 1
	
	if WeaponPicker == 0:
		WeaponType = Weapon0
	elif WeaponPicker == 1:
		WeaponType = Weapon1
	elif  WeaponPicker > 1 or WeaponPicker < 0:
		WeaponType = Weapon0
		WeaponPicker = 0
	print ("Weapon type: ", + WeaponType)


func _on_attack_delay_timer_timeout():
	AttackDelayBar.value = AttackDelayBar.max_value
	AttackTimerSwitch()
	CanThrow = true

func AttackTimerSwitch():
	AttackTimerChunk = AttackDelayBar.max_value / AttackDelayTimer.wait_time
func AttackDelayBarUpdater():
	AttackDelayBar.value = AttackDelayBar.max_value - (AttackTimerChunk * AttackDelayTimer.time_left)
