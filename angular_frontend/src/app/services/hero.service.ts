import { Injectable } from '@angular/core';
import { Hero } from '../hero';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';

@Injectable()
export class HeroService {
  private heroUrl = 'http://localhost:8080/api/hero';  // URL to Spring Backend
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getHeroes(): Observable<Hero[]> {
    return this.http.get<Hero[]>(this.heroUrl + "/all")
      .pipe(
        tap(_ => this.log('fetched heroes')),
        catchError(this.handleError<Hero[]>('getHeroes', []))
      );
  }

  getHero(name: string): Observable<Hero> {

    return this.http.get<Hero>(`${this.heroUrl}/${name}`).pipe(
      tap(_ => this.log(`fetched hero with:${name}`)),
      catchError(this.handleError<Hero>(`getHero`))
    );
  }

  private log(message: string) {
    this.messageService.add(`HeroService: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead

      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  update(hero: Hero, updateHero: Hero): Observable<any> {
    return this.http.put(this.heroUrl + "/update", { hero, updateHero }, this.httpOptions).pipe(
      tap(_ => this.log(`updated hero name=${hero.name}`)),
      catchError(this.handleError<any>('updateHero'))
    );
  }

  create(hero: Hero): Observable<Hero> {
    return this.http.post<Hero>(this.heroUrl, hero, this.httpOptions).pipe(
      tap((newHero: Hero) => this.log(`added hero w/ id=${newHero.name}`)),
      catchError(this.handleError<Hero>('addHero'))
    );
  }

  delete(hero: Hero): Observable<Hero> {
    let queryParams = new HttpParams()
      .append('name', hero.name)
      .append('power', hero.power);

    return this.http.delete<Hero>(`${this.heroUrl}`, {params: queryParams}).pipe(
      tap(_ => this.log(`deleted hero with ${hero}`)),
      catchError(this.handleError<Hero>('deleteHero'))
    );
  }

  search(term: string): Observable<Hero[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<Hero[]>(`${this.heroUrl}/search/${term}`).pipe(
      tap(x => x.length ?
        this.log(`found heroes matching "${term}"`) :
        this.log(`no heroes matching "${term}"`)),
      catchError(this.handleError<Hero[]>('searchHeroes', []))
    );
  }

  getBestHeroes(size: number) {
    let queryParams = new HttpParams()
      .append('size', size)

    return this.http.get<Hero[]>(this.heroUrl + "/best", {params: queryParams})
      .pipe(
        tap(_ => this.log('fetched best heroes')),
        catchError(this.handleError<Hero[]>('getBestHeroes', []))
      );
  }
  getByPagination(size: number, page: number) {
    let queryParams = new HttpParams()
      .append('size', size)
      .append('page', page)

    return this.http.get<Hero[]>(this.heroUrl + "/all/paged", {params: queryParams})
      .pipe(
        tap(_ => this.log(`fetched with pagination(${page} - ${size}) heroes`)),
        catchError(this.handleError<Hero[]>('getByPagination', []))
      );
  }
}
