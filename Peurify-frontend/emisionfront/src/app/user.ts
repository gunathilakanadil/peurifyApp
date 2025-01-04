export class User {
    id!:number;
    name:string;
    phoneNumber: string;
    rewards:number;
    nic:string;
    password:string

    constructor(name:string="",phoneNumber:string="",rewards:number=1,nic:string="",pass:string=""){
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.rewards=rewards;
        this.nic=nic;
        this.password=pass;
    }

}
