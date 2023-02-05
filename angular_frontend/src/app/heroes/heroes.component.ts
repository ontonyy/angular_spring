import { Component, OnInit } from '@angular/core';

import { Hero } from '../hero';
import { HeroService } from '../services/hero.service';
import { MessageService } from '../services/message.service';
import {HeroesStorageService} from "../services/heroes-storage.service";
import * as http from "http";

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  selectedHero?: Hero;

  heroes: Hero[] = [];

  constructor(private heroService: HeroService, private messageService: MessageService, private heroesStorage: HeroesStorageService) { }

  ngOnInit(): void {
    this.heroes = this.heroesStorage.getHeroes()
  }

  onSelect(hero: Hero): void {
    this.selectedHero = hero;
    this.messageService.add(`HeroesComponent: Selected hero id=${hero.name}`);
  }

  add(name: string, newPower: string): void {
    name = name.trim();
    const power = Number(newPower)

    if (!name) { return; }
    this.addHeroToStorage({name, power} as Hero)
    this.heroService.create({ name, power } as Hero)
      .subscribe(hero => {
        console.log(hero)
      });
  }

  addHeroToStorage(hero: Hero) {
    this.heroesStorage.addHero(hero)
    this.heroes = this.heroesStorage.getHeroes()
    console.log(this.heroes)
  }

  delete(hero: Hero): void {
    this.heroes = this.heroes.filter(h => h !== hero);
    this.heroService.delete(hero).subscribe();
    this.heroesStorage.deleteHero(hero);
    this.heroes = this.heroesStorage.getHeroes()
  }
}
