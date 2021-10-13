import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Comic } from './models/comic';
import { ComicService } from './services/comic.service';

// popover file imports
import { PopoverController } from '@ionic/angular';  
 import { PopovercomponentPage } from '../popovercomponent/popovercomponent.page';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit {

  public comics: Array<Comic> = [];
  public myComic: Comic;

  constructor(private router: Router, private comicService: ComicService, private popover: PopoverController) {}

  CreatePopover(){
    this.popover.create({component:PopovercomponentPage,
      showBackdrop:false}).then((popoverElement)=>{
        popoverElement.present();
      })
  }

    ngOnInit(): void {
      this.loadInfo();
    }

    loadInfo() {
      this.comicService.getComics().subscribe((comic: Array<Comic>) => {
        this.comics = comic
      })

      /*this.comicService.getComicById(1).subscribe( (comic: Comic) => {
        this.myComic = comic;
        console.log("my own code has the name: " + comic.name);
      });*/
    }
    
    goToOtherPage(){
      this.router.navigateByUrl("/other-page");
    }

    addAnotherComic(newName: string, newGenre: string, newPanels: number, newUpdated: string, newDescription: string, newWebpage: string){
      console.log("add Another Comic");
      const newComic: Comic = {
        id: null,
        name: newName,
        genre: newGenre,
        panels: newPanels,
        updated: newUpdated,     // "1990-05-12 23:59:59"
        description: newDescription,
        webpage: newWebpage
      };
      this.comicService.createComic(newComic).subscribe(()=> {
        this.loadInfo();
      });
    }
  
    updateComic(idComic: number, newName: string, newGenre: string, newPanels: number, newUpdated: string, newDescription: string, newWebpage: string){
      this.comicService.updateComic(idComic, newName, newGenre, newPanels, newUpdated, newDescription, newWebpage).subscribe(() => {
        this.loadInfo();
      })
    }

    deleteComic(idComic: number){
      this.comicService.deleteComic(idComic).subscribe(() => {
        this.loadInfo();
      })
    }
  
}