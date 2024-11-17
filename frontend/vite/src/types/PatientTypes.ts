type MedicalHistory = {

}

export type  PatientRequestDTO = {
    name:string
    gender:string
    email:string
    birth:string //yyyy-mm-dd
    phone:string
    password:string
    roles:string
    medicalHistory:MedicalHistory
    doctorKey:string
} 


/* 

PatientRequestDTO (String name, String email, String gender,
                                 LocalDate birth, String phone,
                                 String password, UserRoles roles, MedicalHistory medicalHistory,
                                 DigitalSignature signature,
                                 String doctorKey){
}
*/