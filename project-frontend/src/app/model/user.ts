import {PurchasedPass} from './purchasedPass';
import {Cart} from './cart';

export class User {
  id: number;
  emailAddress: string;
  password: string;
  isAdmin: number;
  purchasedPasses: PurchasedPass[];
  car: Cart;
}
