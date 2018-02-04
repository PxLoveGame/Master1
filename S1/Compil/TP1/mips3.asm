#Afficher les N premiers nombres.

.data
message: .asciiz"Saisir le nombre d'entier à afficher !"

.text
main :	li $v0, 4
	la $a0, message
	syscall
	li $v0, 5
	syscall
	move $t1, $v0
	move $t2, $zero
	addi $t2, $t2, 1
 
	
boucle:
	bgt $t2, $t1, end

	li $v0, 1
	move $a0, $t2
	syscall
	addi $t2, $t2, 1
	j boucle
	
end: