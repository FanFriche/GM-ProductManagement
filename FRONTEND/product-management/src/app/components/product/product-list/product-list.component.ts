import { ProductDeleteComponent } from './../product-delete/product-delete.component';
import { ProductService } from './../../../service/product.service';
import { Product } from './../../../model/product.model';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CATEGORIES } from '../../../utils/constants';
import { PageEvent } from '@angular/material/paginator';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  length = 100;
  pageSize = 5;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    console.log(setPageSizeOptionsInput);
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
  
  onPageChange(pageEvent: PageEvent) {
    console.log(this.pageSize);
    this.readProductMetadata();
    this.readProducts(pageEvent.pageIndex, pageEvent.pageSize);
  }

  product: Product = {
    name: '',
    category: 'perecivel',
    value: null
  }

  products: Product[];

  displayedColumns = ['id', 'name', 'category', 'price', 'action']

  constructor(
    private productService: ProductService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.readProductMetadata();
    this.readProducts(0, this.pageSize);
  }

  openDialog(id: string, name: string): void {
    const dialogRef = this.dialog.open(ProductDeleteComponent, {
      width:'600px',
      data: {id: id, name: name}
    });

    dialogRef.afterClosed().subscribe(() => {
      this.readProductMetadata();
      this.readProducts(0, this.pageSize);
    })
  }

  cleanFilter():void {
    this.product = {
      name: '',
      category: 'perecivel',
      value: null
    };
    
    this.readProducts(0, this.pageSize);
  }

  readProducts(page:number, size:number): void {
    this.productService.read(page, size).subscribe(products => {
      this.products = products
    });
  }

  readProductMetadata(): void{
    this.productService.readProductMetadata().subscribe(productMetadata => {
      this.length = productMetadata.totalNumberOfPages;
    });
  }

  readProductsByFilter(): void {
    this.productService.readByFilter(this.product).subscribe(products => {
      this.products = products
    });
  }

  getFormattedCategoryValue(category: string): string {
    if(category === CATEGORIES.UNFORMATTED.PERISHABLE){
      return CATEGORIES.FORMATTED.PERISHABLE;
    }
    return CATEGORIES.FORMATTED.NOT_PERISHABLE
  }
}
