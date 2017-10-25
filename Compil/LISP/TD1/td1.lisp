; ==== 1 - Lambda ====
((lambda (x) (+ (* 2 x) 3)) 4)
((lambda (x y) (* (+ x 2) (+ y 6))) 7 8)
((lambda (v) ((lambda (x) (* 3 x)) (+ v 2))) 8)
((lambda (v) ((lambda (x) (* v x)) (+ v 2))) 8)
((lambda (v) ((lambda (v) (* 3 v)) (+ v 2))) 8)
((lambda (x y z) (+ (* x y) (* y z))) 2 3 4)
((lambda (x y) (* x x y y)) 4 5)
((lambda (x) (* x x 2)) 4)
((lambda (x) (* x x 2)) 4) ;ou
((lambda (x) (* x x)) 2)

; ==== 2 - Fonctions globales ====

(defun f (x) (+ 3 x))
(defun g (v) (* 5 (f (+ v 2))))
(g 8)

;fact
(defun fact (n) (
    if(= n 1)
      n
      (* n (fact (- n 1))
    )
  )
)
(fact 5)

;fibonacci (complexité : fibo elle même)
(defun fibo (n)
  (
    if(<= n 1)
      1
      (+ (fibo(- n 1)) (fibo(- n 2))
    )
  )
)


;==== 3 - Listes et cellules ====
;car retourne le premier element, cdr retourne tout sauf le premier
(car()) ; return NIL
(car'(())) ; return NIL
(car'((()))) ; return (NIL) = NIL

(1 2 3 4) ; 4 cellules  Doublet : [1]->[2]->[3]->[4] Pair pointé : (1.(2.(3.(4.NIL))))
(1 (2 3) 4) ; 5 cellules Doublet : [1]->[(->[2]->[3])]->[4] Pair pointé : (1.(2.(3.NIL).(4.NIL)))
(1 (2) (3) 4) ; 6 cellules Doublet  : [1]->[(->[2])]->[(->[3])]->[4]  ([] représente une cellules.) Pair pointé : (1.(2.NIL).(3.NIL).(4.NIL))

;==== 4 - fonctions sur les listes plates ====
(setf l '(1 2 3 4 5 6 7 8 9))

(defun mymember (x l)(
  if(eql car(l) x)
    l
    ((mymember (x cdr(l))
  )
)))

(defun mylength (l)
(
  if(eql l NIL )
    0
    (+ 1 (mylength (cdr l)))
  )
)

(defun mylast (l)
(
  if(eql (cdr l) NIL )
    (car l)
    (mylast (cdr l))
))

(defun mymakelistreverse (n)
(
  if(eql n 0)
    NIL
    (cons n (mymakelistreverse (- n 1)))
)
)

(defun mymakelist (n)
(
  reverse (mymakelistreverse n)
)
)
