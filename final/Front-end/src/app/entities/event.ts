import {User} from './user';
import {Group} from './group';

export class Event {
  public id: number;
  public name: string;
  public body: string;
  public date: Date;
  public user: User;
  public group: Group;
}
