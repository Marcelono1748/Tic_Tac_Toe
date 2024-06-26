extends CharacterBody2D
var pstats = null
var Limiter = null
var limit = false
var hitarea = null
func _ready():
	pstats = $Hitbox
	hitarea = $Hitbox/CollisionShape2D
	Limiter = $Limiter
	Limiter.wait_time = pstats.limit
	Limiter.start()
func _physics_process(_delta):

	move_and_collide(velocity.normalized()*pstats.spd)
	if limit == true:
		sizer()
func sizer():
	hitarea.set_deferred("disabled",true)
	pstats.spd = 0
	scale = scale * pstats.size
	pstats.size = pstats.size - 0.05
	if pstats.size <= 0:
		queue_free()
func _on_hitbox_area_entered(_area):
	limit = true

func _on_limiter_timeout():
	limit = true
