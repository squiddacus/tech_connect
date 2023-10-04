const TableBody = ({ columns, tableData }) => {
    return (
     <tbody>
      {tableData.map((data) => {
       return (
        <tr key={data.id}>
         {columns.map(({ accessor,link }) => {
          const tData = data[accessor] ? data[accessor] : "——";
          if (link) {
            const linkExpanded = link.replace(":{" + accessor + "}", tData);
            return <td key={accessor}><a href={linkExpanded}>{tData}</a></td>;            
          } else {            
            return <td key={accessor}>{tData}</td>;
          }
         })}
        </tr>
       );
      })}
     </tbody>
    );
   };
   
   export default TableBody;