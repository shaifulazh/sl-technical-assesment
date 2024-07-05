import { NgFor, NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ChangeDetectionStrategy, Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterOutlet } from '@angular/router';

interface Task {
  id : number;
  title : string;
  description : string
}
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,NgFor,FormsModule,NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ui';
  task : Task[]  | any = {} ;
  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.http.get<Task[]>('http://localhost:8080/api/tasklist').subscribe(( task ) => {
    console.log(task)
    if (task ){
      this.task =task
    }else {
      this.task = []
    }
  })}

  ngOnChanges(changes: any) {
    console.log("something hhappen")
    this.ngOnInit()
  }
  onSubmit(f: NgForm){
    const body = {
      title :f.value.title,
      description : f.value.description
    }
    // console.log("test: ", f.value.title)
    this.http.post<any>('http://localhost:8080/api/tasklist',  body).subscribe(
      (res) => {
        this.ngOnInit()
      },
      (err) => {
        this.ngOnInit()
      }
      
    );
  }
  onDelete(id :any) {
    this.http.delete<any>(`http://localhost:8080/api/tasklist/${id}`).subscribe(
      (res) => {
        this.ngOnInit()
      },
      (err) => {
        this.ngOnInit()
      }
      
    );
  }
  onDone(body :any) {
    body.completed = true
    this.http.put<any>(`http://localhost:8080/api/tasklist/${body.id}`,body).subscribe(
      (res) => {
        this.ngOnInit()
      },
      (err) => {
        this.ngOnInit()
      }
      
    );
  }
}
