#saisi d'un tableau, ajout de valeur a ce dernier et permutation.

.data
array: .space 12		#définition la taille du tableau (ici 3 case de 4 octets) 
backspace: .asciiz "\n"
val1: .asciiz "Valeur 1: "
val2: .asciiz "Valeur 2: "
val3: .asciiz "Valeur 3: "

.text
main:	
	la $t0, array		#allocation de la taille du tableau $t0
	jal saisi
	jal lecture
	jal permute
	jal lecture
	j end
	
saisi:	li $v0, 5		#paramètre de saisi de int
	syscall			
	move $t1, $v0		#copie le contenu de $v0 dans $t1
	sw $t1, ($t0)		#ecris $t1 dans le tableau $t0
	
	li $v0, 5
	syscall
	move $t1, $v0
	sw $t1, 4($t0)
	
	li $v0, 5
	syscall
	move $t1, $v0
	sw $t1, 8($t0)
	
	jr $ra
	
lecture:
	li $v0, 4		#paramètre de lecture de String de la sortie standard
	la $a0, val1		
	syscall
	li $v0, 1		#paramètre de lecture de Int sur la sortie standard
	lw $a0, ($t0)		#lecture du 1er l'élement du tableau 
	syscall
	li $v0, 4
	la $a0, backspace
	syscall
	
	li $v0, 4
	la $a0, val2
	syscall
	li $v0, 1
	lw $a0, 4($t0)		#lecture du 2eme l'élement du tableau 
	syscall
	li $v0, 4
	la $a0, backspace
	syscall

	li $v0, 4
	la $a0, val3
	syscall
	li $v0, 1 
	lw $a0, 8($t0)		#lecture du 3eme l'élement du tableau 
	syscall 
	li $v0, 4 
	la $a0, backspace
	syscall
	
	jr $ra
	
permute:
	li $v0, 4 
	la $a0, backspace
	syscall
	
	lw $t1, ($t0)
	lw $t2, 4($t0)
	lw $t3, 8($t0)
	
	sw $t1, 4($t0)
	sw $t2, 8($t0)
	sw $t3, ($t0)
	jr $ra
	
	li $v0, 4 
	la $a0, backspace
	syscall

end: 