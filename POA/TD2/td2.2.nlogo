patches-own[cpt-temps cpt-temps-init taille-plante grow?]
breed[junkies junky]
breed[dealers dealer]
breed[cops cop]
junkies-own[energie]
cops-own[energie]

;; if random-float 100 < density

to setup
  clear-all
  set-default-shape turtles "person"
  ;; randomly distribute wood chips
  ask patches
  [
   set cpt-temps-init random-float temps-croissance-max
   set cpt-temps cpt-temps-init
   set taille-plante 0
   set grow? true
   set pcolor black
  ]
  create-junkies nb-junkies-max
  [
    set color pink
    setxy random-xcor random-ycor
    set size 2
    set energie 100
  ]
  create-dealers nb-dealers-max
  [
    set color brown
    setxy random-xcor random-ycor
    set size 3
  ]
  create-cops nb-cops-max
  [
    set color blue
    setxy random-xcor random-ycor
    set size 2
    set energie 0
  ]
end

to go_peoples
  ask junkies [go_junkies]
  ask dealers [go_dealers]
  ask cops [go_cops]
  update-plots
end

to go_patches
life-of-weed
live-or-die
die-of-weed
end

to go_junkies

  follow-dealer
  smoke
end

to go_dealers
    wiggle
end

to go_cops
  wiggle
  search-and-destroy-junkies
end


to life-of-weed
  if grow? = true
  [
    ifelse cpt-temps > 0
    [
      set cpt-temps cpt-temps - 1
    ]
    [
      set taille-plante taille-plante + 1
      set pcolor scale-color green taille-plante 0 70
      set cpt-temps cpt-temps-init
    ]
  ]
end

to die-of-weed
  if grow? = false
  [
    set taille-plante taille-plante - 1
    set pcolor scale-color green taille-plante 0 70
  ]

end

to live-or-die
  if taille-plante = taille-plante-max
  [
    set grow? false
  ]
  if taille-plante = 0
  [
    set grow? true
  ]
end

to wiggle
  fd 1.5
  rt random 50
  lt random 50
end

to smoke
  if pcolor != black
  [
    if taille-plante >= conso-junk
    [
      set taille-plante taille-plante - conso-junk
      set pcolor scale-color green taille-plante 0 70
      set energie energie + smoke-gain
    ]
  ]
end

to follow-dealer
 Let dealer min-one-of dealers in-radius 15 [distance myself]
 ifelse dealer != nobody
 [
   face dealer
   fd 1
 ]
 [
   wiggle
 ]
end

to search-and-destroy-junkies
  Let target min-one-of junkies in-radius cops_radius [distance myself]
  if target != nobody
  [
    face target
    fd 1.5
  ]
  set target min-one-of junkies in-radius 0.2 [distance myself]
  if target != nobody
  [
    ask target [die]
    set energie energie + kill-gain
    get-away
  ]
end

to get-away
  rt random 360
  fd 25
end
