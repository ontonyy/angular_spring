import { Injectable } from '@angular/core';
import {Hero} from "../hero";

@Injectable({
  providedIn: 'root'
})
export class HeroesStorageService {
  heroes: Hero[] = []

  constructor() { }

  addHero(hero: Hero) {
    this.heroes.push(hero);
  }

  getHeroes(): Hero[] {
    return this.heroes.slice(0, 5);
  }

  deleteHero(hero: Hero) {
    this.heroes.splice(this.heroes.indexOf(hero), 1);

  }
}
