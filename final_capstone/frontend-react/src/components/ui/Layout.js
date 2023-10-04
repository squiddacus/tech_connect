import React from 'react';
import Header from './Header';

const Layout = ({ children }) => {
	return (
		<>
			<Header />
			<div style={{justifyContent: "center", alignContent: "center"}}>
			{ children }
			</div>
			
		</>
	);
};

export default Layout;
