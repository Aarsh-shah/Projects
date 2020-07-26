import pygame
import random
import math
from pygame import  mixer
pygame.init()

#screen
screen=pygame.display.set_mode((800,600))
running=True
pygame.display.set_caption('THE STAR WARS')
icon=pygame.image.load('villain.png')
pygame.display.set_icon(icon)
playerimg=pygame.image.load('spaceship.png')
#backgroundimage
backgroundimg=pygame.image.load('background.png')
mixer.music.load("crrs.wav")
mixer.music.play(-1)
bulletimg=pygame.image.load('bullet.png')
#player
playerx=368
playery=500
playerx_change=0

#enemy
enemyimg=[]
enemyy=[]
enemyx=[]
enemyy_change=[]
enemyx_change=[]
no_of_enemies=6
for i in range(no_of_enemies):
    enemyimg.append(pygame.image.load('skull.png'))
    enemyx.append(random.randint(0,735))
    enemyy.append(random.randint(50,150))
    enemyx_change.append(2)
    enemyy_change.append(40)

#bullet
bulletx=0
bullety=500
bullety_change=7
bullet_state="ready"

#score
scoreval=0
testx=10
testy=10
font=pygame.font.Font('freesansbold.ttf',32)

def score_show(x,y):
    score=font.render("Score: "+str(scoreval),True,(0,255,0))
    screen.blit(score,(x,y))

def player(a,b):
    screen.blit(playerimg,(a,b))

def enemy(a,b,i):
    screen.blit(enemyimg[i],(a,b))

def fire_bullet(x,y):
    global bullet_state
    bullet_state="fire"
    screen.blit(bulletimg,(x+16,y+10))

def is_collision(enemyx,enemyy,bulletx,bullety):
    val=math.sqrt(((bulletx-enemyx)**2)+(bullety-enemyy)**2)
    if val<=27:
        return True
    else:
        return False
over_font=pygame.font.Font('freesansbold.ttf',48)
#check for game over
def game_over():
    over_text=over_font.render('GAME OVER!',True,(255,255,255))
    screen.blit(over_text,(200,250))


while running:
    screen.fill((0, 0, 0))
    screen.blit(backgroundimg,(0,0))
    for event in pygame.event.get():
        if event.type== pygame.QUIT:
            running=False
        if event.type== pygame.KEYDOWN:
            if event.key==pygame.K_LEFT:
                playerx_change=-5
            if event.key==pygame.K_RIGHT:
                playerx_change=5
            if event.key==pygame.K_SPACE:
                if bullet_state is "ready":
                    bullet_sound=mixer.Sound("laser.wav")
                    bullet_sound.play()
                    bulletx=playerx
                    fire_bullet(bulletx,bullety)

        if event.type==pygame.KEYUP:
            if event.key==pygame.K_LEFT or event.key==pygame.K_RIGHT:
                playerx_change=0


    playerx+=playerx_change
    if bullety<=0:
        bullety=500
        bullet_state="ready"

    if bullet_state is "fire":
        fire_bullet(bulletx,bullety)
        bullety-=bullety_change


    if playerx >= 736:
        playerx=736
    if playerx<=0:
        playerx=0

    for i in range(no_of_enemies):
        if enemyy[i]>400:
           for j in range(no_of_enemies):
               enemyy[j]=2000
           game_over()
           break

        enemyx[i] += enemyx_change[i]
        if enemyx[i] >= 736:
            enemyx_change[i]=-2
            enemyy[i]+=enemyy_change[i]
        if enemyx[i]<=0:
            enemyx_change[i]=2
            enemyy[i]+=enemyy_change[i]
        collision = is_collision(enemyx[i], enemyy[i], bulletx, bullety)
        if collision:
            collision_sound=mixer.Sound("explosion.wav")
            collision_sound.play()
            bullet_state = "ready"
            bullety = 500
            scoreval += 1
            enemyx[i] = random.randint(0, 735)
            enemyy[i] = random.randint(50, 150)

        enemy(enemyx[i],enemyy[i],i)
    player(playerx,playery)
    score_show(testx,testy)
    pygame.display.update()