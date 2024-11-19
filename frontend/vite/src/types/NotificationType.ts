import { AttachmentFindResponseDTO } from "./ResearchTypes";
export type NotificationResponseDTO = {
  title: string;
  message: string;
  attachments: AttachmentFindResponseDTO;
};
