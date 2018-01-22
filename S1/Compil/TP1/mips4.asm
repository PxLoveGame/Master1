.data
saisie: .asciiz"Saisir un nombre !"
true: .asciiz"Ce nombre est pair"
false:.asciiz"Ce nombre est impair"
backspace: .asciiz "\n"

.text

main:
	li $v0, 4
	la $a0, saisie
	syscall
	
	#saisi par l'utilisateur
	li $v0, 5
	syscall
	move $t1, $v0

modulo:	
	li $t2, 2
	#n modulo p = r
	#q = n/p
	#r = n - q*p
	div $t3,$t1,$t2		#recupère q
	mul $t4, $t3,$t2	#(q*p)
	sub $t4, $t1, $t4 	#ceci est le reste (r)
	
	#li $v0, 1
	#move $a0, $t4
	#syscall
	
	li $v0, 4 
	la $a0, backspace
	syscall
	
	beq $t4 , 1, impair
	
	li $v0, 4
	la $a0, true
	syscall
	j end
	
impair:
	li $v0, 4
	la $a0, false
	syscall
	 
end:
	 
