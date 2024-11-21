import { AttachmentFindResponseDTO } from "./ResearchTypes";
export type NotificationResponseDTO = {
  title: string;
  message: string;
  sender:string
  attachment: AttachmentFindResponseDTO[];
};
