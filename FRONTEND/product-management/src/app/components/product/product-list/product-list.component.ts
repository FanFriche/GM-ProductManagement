import { ProductDeleteComponent } from './../product-delete/product-delete.component';
import { ProductService } from './../../../service/product.service';
import { Product } from './../../../model/product.model';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CATEGORIES } from '../../../utils/constants';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[];
  displayedColumns = ['id', 'name', 'category', 'price', 'action']

  constructor(
    private productService: ProductService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.readProducts()
  }

  openDialog(id: string, name: string): void {
    const dialogRef = this.dialog.open(ProductDeleteComponent, {
      width:'600px',
      data: {id: id, name: name}
    });

    dialogRef.afterClosed().subscribe(() => {
      this.readProducts();
    })
  }

  readProducts(): void {
    this.productService.read().subscribe(products => {
      this.products = products
    })
  }

  getFormattedCategoryValue(category: string): string {
    if(category === CATEGORIES.UNFORMATTED.PERISHABLE){
      return CATEGORIES.FORMATTED.PERISHABLE;
    }
    return CATEGORIES.FORMATTED.NOT_PERISHABLE
  }

}
