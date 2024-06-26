extends Node
signal hurtboxDisable
signal hurtboxEnable
signal SpriteVisibleFalse
signal SpriteVisibleTrue

func blink():
	var timer = 0.1
	var Iframe = 0.2
	var fade = 2
	var end = 0
	
	
	
	emit_signal("hurtboxDisable")
	
	for i in fade:
		emit_signal("SpriteVisibleFalse")
		await get_tree().create_timer(timer).timeout
		emit_signal("SpriteVisibleTrue")
		await get_tree().create_timer(timer).timeout
		end = end + 1
		
	if end >= fade:
		
		await get_tree().create_timer(Iframe).timeout
		end = 0
		emit_signal("hurtboxEnable")
