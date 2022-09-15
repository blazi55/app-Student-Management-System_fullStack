import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button, Paper } from '@mui/material';
import {useState, useEffect} from 'react';

export default function Student() {
    const papierStyle = {padding:'50px 20px', width: 600, margin: "20px auto"}
    const textStyle = {margin: "10px"}
    const[name, setName] = useState('')
    const[address, setAddress] = useState('')
    const[student, setStudent] = useState([])
    const[studentId, setStudentId] = useState(0)


    const onSave = (e) => {
        e.preventDefault()
        const student = {name, address}
        console.log(student)
        fetch("http://localhost:8080/student", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(student)
        }).then(() => {
            console.log("Added")
            console.log(studentId)
        })
    }

    const onDelete = () => {
      fetch(`http://localhost:8080/student/${studentId}`, {
          method: "DELETE",
      }).then(() => {
          console.log("Dead")
          console.log(studentId)
      })
  }

    useEffect(() => {
      fetch("http://localhost:8080/student/all")
      .then(res => {
        return res.json();
      }).then(data => {
        setStudent(data);
      });
    }/*mozna dodac []*/);

  return (
    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <Paper elevation={3} style={papierStyle}>
        <TextField id="outlined-basic" label="Student Name" variant="outlined" value={name} onChange={(e) => setName(e.target.value)}
        fullWidth style={textStyle}/>
        <TextField id="outlined-basic" label="Address" variant="outlined" value={address} onChange={(e) => setAddress(e.target.value)} 
        fullWidth style={textStyle}/>
        <Button variant="outlined" onClick={onSave}> Submit </Button>
        <div className='studentId'>
          Write Id to delete Student
          </div>
        <TextField id="outlined-basic" label="StudentId" variant="outlined" value={studentId} onChange={(e) => setStudentId(e.target.value)} 
            fullWidth style={textStyle}/>
        <Button variant="outlined" onClick={onDelete}> Delete Student </Button>
      </Paper>
      <Paper elevation={3} style={papierStyle}>
        <div>Students Data: </div>
        {student.map(st => (
          <Paper elevation={3} style={{margin:"10px", padding:"15px", textAlign:"center"}} key={st.id}>
            <div> <b>UserName:</b> {st.name}</div>
            <div>-------------------------</div>
            <div> <b>Adress:</b> {st.address}</div>
          </Paper>
        ))}
      </Paper>
    </Box>
  );
}
