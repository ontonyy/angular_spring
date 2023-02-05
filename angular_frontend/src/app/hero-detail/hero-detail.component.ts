import { Component, Input } from '@angular/core';
import { Hero } from '../hero';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { HeroService } from '../services/hero.service';

@Component({
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.css']
})
export class HeroDetailComponent {
  updateHero?: Hero;
  oldName?: string
  oldPower?: Number

  constructor(
    private route: ActivatedRoute,
    private heroService: HeroService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getHero();
  }

  getHero(): void {
    const name = this.route.snapshot.paramMap.get('name');
    this.heroService.getHero(name as string)
      .subscribe(hero => {
        this.oldName = hero.name
        this.oldPower = hero.power
        this.updateHero = hero
      });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.updateHero) {
      this.heroService.update({"name": this.oldName, "power": this.oldPower} as Hero, this.updateHero as Hero)
        .subscribe(() => this.goBack());
    }
  }

}

