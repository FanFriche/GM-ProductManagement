import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar'
import { Product } from './../model/product.model';
import { ProductMetadata } from './../model/product-metadata.model'
import { ThrowStmt } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class ProductService{

  baseUrl = "http://localhost:8080/api/products/"

  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  showMessage(msg: string, isError: boolean = false, duration: number = 3000): void{
    this.snackBar.open(msg, 'x', {
      duration: duration,
      horizontalPosition: "center",
      verticalPosition: "top",
      panelClass: isError ? ['msg-error'] : ['msg-success']
    })
  }
  
  create(product: Product): Observable<Product>{
    return this.http.post<Product>(this.baseUrl, product).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  update(product: Product): Observable<Product>{
    const url = `${this.baseUrl}/${product.id}`;
    return this.http.put<Product>(url, product).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  read(page:number, size:number): Observable<Product[]>{
    const url = `${this.baseUrl}/${page}/${size}`
    return this.http.get<Product[]>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  readById(id: string):Observable<Product>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Product>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  readProductMetadata():Observable<ProductMetadata>{
    const url = `${this.baseUrl}/paginator`;
    return this.http.get<ProductMetadata>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  readByFilter(product: Product):Observable<Product[]>{
    let url = `${this.baseUrl}filter?nomeProduto=${product.name}&categoria=${product.category}`;

    /*if(product.name !== '' && product.name !== null && product.name !== undefined){
      url += `nomeProduto=${product.name}`;
    } else if(product.category !== '' && product.category !== null && product.category !== undefined) {
      url += `?categoria=${product.category}`;
    }*/
    return this.http.get<Product[]>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  delete(product: Product):Observable<Product>{
    const url = `${this.baseUrl}/${product.id}`;
    return this.http.delete<Product>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage('Ocorreu um erro!', true)
    return EMPTY;
  }
}
