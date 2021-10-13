import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comic } from '../models/comic';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const httpOptionsUsingUrlEncoded = {      // enviar datos como spring lo recibe automáticamente (x-www-form-urlencoded)
    headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' })
};

@Injectable({
    providedIn: 'root'
})

export class ComicService {

    endpoint: string = "http://localhost:8080/comics";

    constructor(private httpClient: HttpClient) { }

    getComics(): Observable<Comic[]>{
        return this.httpClient.get<Comic[]>(this.endpoint);
    }

    getComicById(id: number): Observable<Comic>{
        return this.httpClient.get<Comic>(this.endpoint + "/" + id);
    }

    createComic(newComic: Comic): Observable<Comic>{
        let bodyEncoded = new URLSearchParams();
        bodyEncoded.append("name", newComic.name);
        bodyEncoded.append("genre", newComic.genre);
        bodyEncoded.append("panels", newComic.panels.toString());
        bodyEncoded.append("updated", newComic.updated);
        bodyEncoded.append("description", newComic.description);
        bodyEncoded.append("webpage", newComic.webpage);
        const body = bodyEncoded.toString();

        console.log("creating a new comic")
        console.log(JSON.stringify(newComic))
        return this.httpClient.post<Comic>(this.endpoint, body, httpOptionsUsingUrlEncoded)
    }

    updateComic(idComic: number, newName: string, newGenre: string, newPanels: number, newUpdated: string, newDescription: string, newWebpage: string): Observable<Comic>{
        let bodyEncoded = new URLSearchParams();
        bodyEncoded.append("id", idComic.toString());
        bodyEncoded.append("name", newName);
        bodyEncoded.append("genre", newGenre);
        bodyEncoded.append("panels", newPanels.toString());
        bodyEncoded.append("updated", newUpdated);
        bodyEncoded.append("description", newDescription);
        bodyEncoded.append("webpage", newWebpage);
        const body = bodyEncoded.toString();
        console.log("CASI AL FINAL DEL PUT O ;  " + body)
        return this.httpClient.put<Comic>(this.endpoint + "/" + idComic, body, httpOptionsUsingUrlEncoded);
    }

    deleteComic(idComic: number): Observable<Comic>{
        return this.httpClient.delete<Comic>(this.endpoint + "/" + idComic);
    }
}