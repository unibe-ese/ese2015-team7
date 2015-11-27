package org.sample.model;

import org.springframework.util.AutoPopulatingList.ElementFactory;
import org.springframework.util.AutoPopulatingList.ElementInstantiationException;

/**
 * This factory creates new TimeSlot instances and implements ElementFactory<TimeSlot>.
 * 
 * @author Team7
 *
 */
public class TimeSlotFactory implements ElementFactory<TimeSlot> {
	
		public TimeSlot createElement(int arg0) throws ElementInstantiationException {
			return new TimeSlot();
		}
}
