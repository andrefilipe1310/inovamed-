import { AttachmentFindResponseDTO } from "./ResearchTypes";
export type NotificationResponseDTO = {
  title: string;
  message: string;
  sender:string
  attachment: AttachmentFindResponseDTO[];
};

export type NotificationRequestDTO = {
  title: string;
  message: string;
  doctorsId:number[]
  patientsId:number[]
}
