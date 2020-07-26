import pygame
import random

import sys
from pygame.locals import *

# global variables
FPS = 32
screenWIDTH = 289
screenHEIGHT = 511
screen = pygame.display.set_mode((screenWIDTH, screenHEIGHT))
basey = screenHEIGHT * 0.8
game_images = {}
game_audios = {}
Player = 'bird.png'
background = 'background.png'
pipe = 'pipe.png'
global val
val=0

def welcome():
    playerx = screenWIDTH // 5
    playery = (screenHEIGHT - game_images['player'].get_height()) // 2
    msgx = (screenWIDTH - game_images['message'].get_width()) // 2
    msgy = (screenHEIGHT * 0.15) // 2
    basex = 0
    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if event.type == KEYDOWN and (event.key == K_UP or event.key == K_SPACE):
                return
            else:
                screen.blit(game_images['background'], (0, 0))
                screen.blit(game_images['player'], (playerx, playery))
                screen.blit(game_images['message'], (msgx, msgy))
                screen.blit(game_images['base'], (basex, basey))
                pygame.display.update()
                clock.tick(FPS)


def randompipe():
    pipeheight=game_images['pipe'][0].get_height()
    offset=screenHEIGHT/3
    ly=offset+random.randrange(0,int(screenHEIGHT- game_images['base'].get_height()-1.2*offset))
    pipex=screenWIDTH+10
    uy=pipeheight-ly+offset
    pipe=[
        { 'x':pipex,'y':-ly } , #Lower pipe
        { 'x':pipex,'y':uy}
    ]
    return pipe

def main():
    global val
    val=0
    score = 0
    playerx = screenWIDTH // 5
    playery = screenHEIGHT // 2
    basex=0

    newp1=randompipe()
    newp2=randompipe()
    upperpipes = [
        {'x': screenWIDTH + 200, 'y': newp1[0]['y']},
        {'x': screenWIDTH + 200 + (screenWIDTH / 2), 'y': newp2[0]['y']},
    ]
    # my List of lower pipes
    lowerpipes = [
        {'x': screenWIDTH + 200, 'y': newp1[1]['y']},
        {'x': screenWIDTH + 200 + (screenWIDTH / 2), 'y': newp2[1]['y']},
    ]

    pipeVelX = -4

    playerVelY = -9
    playerMaxVelY = 10
    playerMinVelY = -8
    playerAccY = 1

    playerFlapAccv = -8 # velocity while flapping
    playerFlapped = False #Gets true when the bird is flapped

    while True:
        for event in pygame.event.get():
            if event.type == QUIT or (event.type == KEYDOWN and event.key == K_ESCAPE):
                pygame.quit()
                sys.exit()
            if event.type == KEYDOWN and (event.key == K_SPACE or event.key == K_UP):
                if playery > 0:
                    playerVelY = playerFlapAccv
                    playerFlapped = True
                    game_audios['wing'].play()
        crashTest = isCollide(playerx, playery, upperpipes,
                                      lowerpipes)  # This function will return true if the player is crashed
        if crashTest:
            return
        playerMidPos = playerx + game_images['player'].get_width() / 2
        for pipe in upperpipes:
            pipeMidPos = pipe['x'] +game_images['pipe'][0].get_width() / 2
            if pipeMidPos <= playerMidPos < pipeMidPos + 4:
                score += 1
                print(f"Your score is {score}")
                val=max(val,score)

                game_audios['point'].play()


        if playerVelY < playerMaxVelY and not playerFlapped:
            playerVelY += playerAccY
        if playerFlapped:
            playerFlapped = False
        playerHeight = game_images['player'].get_height()
        playery = playery + min(playerVelY, basey - playery - playerHeight)

        # move pipes to the left
        for upperpipe, lowerpipe in zip(upperpipes, lowerpipes):
            upperpipe['x'] += pipeVelX
            lowerpipe['x'] += pipeVelX

        if 0 < upperpipes[0]['x'] < 5:
            newpipe = randompipe()
            upperpipes.append(newpipe[0])
            lowerpipes.append(newpipe[1])

        if upperpipes[0]['x'] < -game_images['pipe'][0].get_width():
            upperpipes.pop(0)
            lowerpipes.pop(0)

        screen.blit(game_images['background'],(0,0))
        for upperPipe, lowerPipe in zip(upperpipes, lowerpipes):
            screen.blit(game_images['pipe'][0], (upperPipe['x'], upperPipe['y']))
            screen.blit(game_images['pipe'][1], (lowerPipe['x'], lowerPipe['y']))
        screen.blit(game_images['base'],(basex,basey))
        screen.blit(game_images['player'],(playerx,playery))
        Digitlist=[int(x) for x in list(str(score))]
        width=0

        for digit in Digitlist:
            width += game_images['numbers'][digit].get_width()
        Xoffset = (screenWIDTH - width) / 2
        for digit in Digitlist:
            screen.blit(game_images['numbers'][digit],(Xoffset,screenWIDTH*0.12))
            Xoffset+=game_images['numbers'][digit].get_width()

        pygame.display.update()
        clock.tick(FPS)

def isCollide(playerx,playery,upperpipe,lowerpipe):
    if playery > basey - 25 or playery < 0:
        game_audios['hit'].play()
        return True

    for pipe in upperpipe:
        pipeHeight = game_images['pipe'][0].get_height()
        if (playery < pipeHeight + pipe['y'] and abs(playerx - pipe['x']) < game_images['pipe'][0].get_width()):
            game_audios['hit'].play()
            return True

    for pipe in lowerpipe:
        if (playery + game_images['player'].get_height() > pipe['y']) and abs(playerx - pipe['x']) < \
                game_images['pipe'][0].get_width():
            game_audios['hit'].play()
            return True

    return False



if __name__ == "__main__":
    pygame.init()
    clock = pygame.time.Clock()
    clock.tick(40)
    pygame.display.set_caption('FLAPPY-AARSH')
    game_images['numbers'] = (
        pygame.image.load('0.png').convert_alpha(),
        pygame.image.load('1.png').convert_alpha(),
        pygame.image.load('2.png').convert_alpha(),
        pygame.image.load('3.png').convert_alpha(),
        pygame.image.load('4.png').convert_alpha(),
        pygame.image.load('5.png').convert_alpha(),
        pygame.image.load('6.png').convert_alpha(),
        pygame.image.load('7.png').convert_alpha(),
        pygame.image.load('8.png').convert_alpha(),
        pygame.image.load('9.png').convert_alpha(),

    )
    game_images['message'] = pygame.image.load('rrumble.png').convert_alpha()
    game_images['base'] = pygame.image.load('base.png')
    game_images['pipe'] = (pygame.transform.rotate(pygame.image.load(pipe), 180).convert_alpha(),
                            pygame.image.load(pipe).convert_alpha()
        )
    game_images['player'] = pygame.image.load(Player).convert_alpha()
    game_images['background'] = pygame.image.load(background).convert_alpha()
    game_audios['die'] = pygame.mixer.Sound('die.wav')
    game_audios['hit'] = pygame.mixer.Sound('hit.wav')
    game_audios['point'] = pygame.mixer.Sound('point.wav')
    game_audios['wing'] = pygame.mixer.Sound('wing.wav')
    game_audios['swoosh'] = pygame.mixer.Sound('swoosh.wav')

    while True:
        welcome()
        main()
