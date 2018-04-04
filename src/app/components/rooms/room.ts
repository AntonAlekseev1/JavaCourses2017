export class Room {
  constructor(
    public id: number,
    public number: number,
    public copacity: number,
    public stars: number,
    public price: number,
    public isFree: boolean,
    public status: string
  ) {}
}
